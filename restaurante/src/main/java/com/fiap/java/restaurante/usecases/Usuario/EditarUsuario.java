package com.fiap.java.restaurante.usecases.Usuario;

import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.EditaDadosDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarUsuario {
    private final UsuarioRepository usuarioRepository;
    private final RespostaMapper respostaMapper;

    public Usuario editarDados(Long id, EditaDadosDTO editaDadosDTO) {
        Usuario usuarioEncontrado = respostaMapper.usuarioEntityToUsuario(usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado - ID: " + id)));

        usuarioEncontrado.atualizarDados(
                editaDadosDTO.getEmail(),
                editaDadosDTO.getUsuario(),
                editaDadosDTO.getEndereco()
        );

        return respostaMapper.usuarioEntityToUsuario(
                usuarioRepository.save(respostaMapper.usuarioToUsuarioEntity(usuarioEncontrado)));

    }
}
