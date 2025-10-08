package com.fiap.java.restaurante.service;

import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.RestauranteDTO;
import com.fiap.java.restaurante.DTO.RestauranteEditaDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.models.Endereco;
import com.fiap.java.restaurante.models.Restaurante;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.repository.RestauranteRepository;
import com.fiap.java.restaurante.service.mapper.RespostaMapper;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final RespostaMapper respostaMapper = new RespostaMapper();

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO, Usuario usuario) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteDTO.getNome());
        restaurante.setTipoCozinha(restauranteDTO.getTipoCozinha());
        restaurante.setHorarioFuncionamento(restauranteDTO.getHorarioFuncionamento());
        Endereco endereco = respostaMapper.mapEnderecoDTOToEndereco(restauranteDTO.getEndereco());
        endereco.setRestaurante(restaurante);
        restaurante.setEndereco(endereco);
        restaurante.setDonoRestaurante(usuario);

        restauranteRepository.save(restaurante);
        return respostaMapper.mapRestauranteToRestauranteDTO(restaurante);
    }

    public void excluir(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurante não encontrado"));
        restauranteRepository.delete(restaurante);
    }

    public RestauranteDTO buscarPorId(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado - ID: " + id));
        return respostaMapper.mapRestauranteToRestauranteDTO(restaurante);
    }

    public RespostaDTO editarDados(Long id, RestauranteEditaDTO restauranteEditaDTO) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado - ID: " + id));

        if (restauranteEditaDTO.getNome() != null) {
            restaurante.setNome(restauranteEditaDTO.getNome());
        }
        if (restauranteEditaDTO.getTipoCozinha() != null) {
            restaurante.setTipoCozinha(restauranteEditaDTO.getTipoCozinha());
        }
        if (restauranteEditaDTO.getHorarioFuncionamento() != null) {
            restaurante.setHorarioFuncionamento(restauranteEditaDTO.getHorarioFuncionamento());
        }
        if (restauranteEditaDTO.getEndereco() != null) {
            Endereco endereco = respostaMapper.mapEditaDadosEnderecoDTOToEndereco(restauranteEditaDTO.getEndereco(), restaurante.getEndereco());
            endereco.setRestaurante(restaurante);
            restaurante.setEndereco(endereco);
        }

        restauranteRepository.save(restaurante);
        return respostaMapper.mapRestauranteAtualizadoToRespostaDTO(restaurante.getId());
    }
}