package com.fiap.java.restaurante.usecases.Usuario;

import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.TrocaSenhaDTO;
import com.fiap.java.restaurante.exceptions.BadRequestException;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MudarSenhaUsuario {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RespostaMapper respostaMapper;

    public Usuario trocarSenha(Long id, TrocaSenhaDTO dto) {
        Usuario usuarioEncontrado = validarUsuario(id);

        // Campo de senha a ser alterado
        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuarioEncontrado.getSenha())) {
            throw new BadRequestException("Senha atual incorreta");
        }

        if (!dto.getNovaSenha().equals(dto.getConfirmaNovaSenha())) {
            throw new BadRequestException("Nova senha e confirmação não coincidem");
        }

        usuarioEncontrado.atualizarSenha(
                passwordEncoder.encode(dto.getNovaSenha())
        );

        return respostaMapper.usuarioEntityToUsuario(
                usuarioRepository.save(respostaMapper.usuarioToUsuarioEntity(usuarioEncontrado)));

    }

    private Usuario validarUsuario(Long id) {
        return respostaMapper.usuarioEntityToUsuario(usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + id)));
    }
}
