package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirPerfilUsuario {
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final RespostaMapper respostaMapper;
    public void excluir(Long id) {
        PerfilUsuario perfilUsuario = respostaMapper.perfilUsuarioEntityToPerfilUsuario(perfilUsuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Perfil usuario não encontrado - ID: " + id)));
        perfilUsuarioRepository.delete(respostaMapper.perfilUsuariotoPerfilUsuarioEntity(perfilUsuario));
    }
}
