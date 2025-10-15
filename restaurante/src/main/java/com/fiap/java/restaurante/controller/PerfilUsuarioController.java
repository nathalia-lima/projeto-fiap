package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.controller.api.PerfilUsuarioAPI;
import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.dto.PerfilUsuarioDTO;
import com.fiap.java.restaurante.usecases.PerfilUsuario.BuscarPerfilUsuario;
import com.fiap.java.restaurante.usecases.PerfilUsuario.CadastrarPerfilUsuario;
import com.fiap.java.restaurante.usecases.PerfilUsuario.ExcluirPerfilUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
@RequiredArgsConstructor
public class PerfilUsuarioController implements PerfilUsuarioAPI {
    private final CadastrarPerfilUsuario cadastrarPerfilUsuario;
    private final BuscarPerfilUsuario buscarPerfilUsuario;
    private final ExcluirPerfilUsuario excluirPerfilUsuario;

    @PostMapping
    public ResponseEntity<PerfilUsuario> salvar(@RequestBody PerfilUsuarioDTO perfilUsuarioDTO) {
        return ResponseEntity.status(201).body(cadastrarPerfilUsuario.salvar(perfilUsuarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirPerfilUsuario.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilUsuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(buscarPerfilUsuario.buscarPorId(id));
    }


}
