package com.fiap.java.restaurante.usecases.Usuario;

import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarUsuario {
    private final UsuarioRepository usuarioRepository;
    private final RespostaMapper respostaMapper;

    public Usuario buscarPorId(Long id) {
        return respostaMapper.usuarioEntityToUsuario(usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado - ID: " + id)));
    }
}
