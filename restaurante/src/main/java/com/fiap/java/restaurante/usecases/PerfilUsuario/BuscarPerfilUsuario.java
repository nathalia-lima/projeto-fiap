package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarPerfilUsuario {
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final RespostaMapper respostaMapper;

   public PerfilUsuario buscarPorId(Long id) {
       return respostaMapper.perfilUsuarioEntityToPerfilUsuario(perfilUsuarioRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Item do cardapio não encontrado - ID: " + id)));
   }
}
