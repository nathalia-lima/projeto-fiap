package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.controller.api.UsuarioControllerAPI;
import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.CriaUsuarioDTO;
import com.fiap.java.restaurante.dto.EditaDadosDTO;
import com.fiap.java.restaurante.dto.LoginDTO;
import com.fiap.java.restaurante.dto.TrocaSenhaDTO;
import com.fiap.java.restaurante.usecases.Usuario.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
@Tag(name = "UsuarioEntity", description = "Operações para Usuarios")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerAPI {
    private final CadastrarUsuario cadastrarUsuario;
    private final EditarUsuario editarUsuario;
    private final BuscarUsuario buscarUsuario;
    private final ExcluirUsuario excluirUsuario;
    private final LoginUsuario loginUsuario;
    private final MudarSenhaUsuario trocarSenhaUsuario;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody @Valid CriaUsuarioDTO user) {
        logger.info("Recebido DTO: {}", user);
        return ResponseEntity.status(201).body(cadastrarUsuario.salvar(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody @Valid LoginDTO login ) {
        return ResponseEntity.status(200).body(loginUsuario.login(login));
    }

    @PatchMapping("/editar/dados/{id}")
    public ResponseEntity<Usuario> editarDados(@PathVariable Long id, @Valid @RequestBody EditaDadosDTO user) {
        return ResponseEntity.status(200).body(editarUsuario.editarDados(id, user));
    }

    @PatchMapping("/trocar/senha/{id}")
    public ResponseEntity<Usuario> trocarSenha(@PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO user) {
        return ResponseEntity.status(200).body(trocarSenhaUsuario.trocarSenha(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirUsuario.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(buscarUsuario.buscarPorId(id));
    }

}
