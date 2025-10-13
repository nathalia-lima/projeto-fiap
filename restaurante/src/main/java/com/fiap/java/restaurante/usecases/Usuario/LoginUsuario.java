package com.fiap.java.restaurante.usecases.Usuario;

import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.LoginDTO;
import com.fiap.java.restaurante.exceptions.UnauthorizedException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RespostaMapper respostaMapper;

    public Usuario login(LoginDTO loginDTO) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmailIgnoreCase(loginDTO.getEmail())
            .map(respostaMapper::usuarioEntityToUsuario);

        if(optUsuario.isEmpty() || !passwordEncoder.matches(loginDTO.getSenha(), optUsuario.get().getSenha())) {
            throw new UnauthorizedException("Login e/ou senha inválidos");
        }

        return optUsuario.get();
    }
}
