package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.dto.PerfilUsuarioDTO;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarPerfilUsuario {
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final RespostaMapper respostaMapper;

    public PerfilUsuario salvar(PerfilUsuarioDTO perfilUsuarioDTO) {
        PerfilUsuario perfilUsuario = new PerfilUsuario(
                perfilUsuarioDTO.getNome()
        );
        return respostaMapper.perfilUsuarioEntityToPerfilUsuario(
                perfilUsuarioRepository.save(respostaMapper.perfilUsuariotoPerfilUsuarioEntity(perfilUsuario)));
    }
}
