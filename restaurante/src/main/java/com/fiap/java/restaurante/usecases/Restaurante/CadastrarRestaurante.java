package com.fiap.java.restaurante.usecases.Restaurante;

import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.RestauranteDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.exceptions.UnauthorizedException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarRestaurante {
    private final RestauranteRepository restauranteRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final RespostaMapper respostaMapper;

    public Restaurante salvar(RestauranteDTO restauranteDTO) {
        Usuario dono = validarDono(restauranteDTO.getIdDono());

        Restaurante restaurante = new Restaurante(
                restauranteDTO.getNome(),
                restauranteDTO.getEndereco(),
                restauranteDTO.getTipoCozinha(),
                restauranteDTO.getHorarioFuncionamento(),
                dono.getId()
        );

        return respostaMapper.restauranteEntityToRestaurante(
                restauranteRepository.save(respostaMapper.restauranteToRestauranteEntity(restaurante)));
    }

    private Usuario validarDono(Long idDono) {
        Usuario dono = respostaMapper.usuarioEntityToUsuario(usuarioRepository.findById(idDono)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + idDono)));

        PerfilUsuario perfil = respostaMapper.perfilUsuarioEntityToPerfilUsuario(perfilUsuarioRepository.findById(dono.getUsuario())
                .orElseThrow(() -> new NotFoundException("Perfil de usuário não encontrado - ID: " + dono.getUsuario())));

        if (!"DONO_RESTAURANTE".equals(perfil.getNome())) {
            throw new UnauthorizedException("Usuário não é dono - ID: " + idDono);
        }

        return dono;
    }
}
