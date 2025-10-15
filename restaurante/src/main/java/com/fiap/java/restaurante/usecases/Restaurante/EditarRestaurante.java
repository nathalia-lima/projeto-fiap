package com.fiap.java.restaurante.usecases.Restaurante;

import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.dto.RestauranteEditaDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarRestaurante {
    private final RestauranteRepository restauranteRepository;
    private final RespostaMapper respostaMapper;

    public Restaurante editarDados(Long id, RestauranteEditaDTO restauranteEditaDTO) {
        Restaurante restauranteEncontrado = respostaMapper.restauranteEntityToRestaurante(restauranteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurante não encontrado - ID: " + id)));

        restauranteEncontrado.atualizarDados(
                restauranteEditaDTO.getNome(),
                restauranteEditaDTO.getTipoCozinha(),
                restauranteEditaDTO.getHorarioFuncionamento(),
                restauranteEditaDTO.getEndereco()
        );

        return respostaMapper.restauranteEntityToRestaurante(
                restauranteRepository.save(respostaMapper.restauranteToRestauranteEntity(restauranteEncontrado)));

    }
}
