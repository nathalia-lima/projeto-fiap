package com.fiap.java.restaurante.usecases.Usuario;

import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirUsuario {
    private final UsuarioRepository usuarioRepository;
    private final RespostaMapper respostaMapper;
    public void excluir(Long id) {
        Usuario usuario = respostaMapper.usuarioEntityToUsuario(usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado - ID: " + id)));

        usuarioRepository.delete(respostaMapper.usuarioToUsuarioEntity(usuario));
    }
}
