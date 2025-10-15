package com.fiap.java.restaurante.controller.api;

import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.dto.PerfilUsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PerfilUsuarioAPI {
    public ResponseEntity<PerfilUsuario> salvar(@RequestBody PerfilUsuarioDTO perfilUsuarioDTO);
    public ResponseEntity<Void> excluir(@PathVariable Long id);
    public ResponseEntity<PerfilUsuario> buscarPorId(@PathVariable Long id);
}
