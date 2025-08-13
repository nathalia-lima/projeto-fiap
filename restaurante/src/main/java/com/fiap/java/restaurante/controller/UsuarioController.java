package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.controller.UsuarioControllerAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.fiap.java.restaurante.DTO.UsuarioDTO;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.fiap.java.restaurante.DTO.CriaUsuarioDTO;
import com.fiap.java.restaurante.DTO.EditaDadosDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.LoginDTO;
import com.fiap.java.restaurante.DTO.TrocaSenhaDTO;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Operações para Usuarios")
public class UsuarioController implements UsuarioControllerAPI {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid CriaUsuarioDTO criaUsuarioDTO) {
        return ResponseEntity.status(201).body(usuarioService.salvar(criaUsuarioDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaDTO> login(@RequestBody @Valid LoginDTO login ) {
        return ResponseEntity.status(200).body(usuarioService.login(login));
    }

    @PatchMapping("/editar/dados/{id}")
    public ResponseEntity<RespostaDTO> editarDados(@RequestHeader String authorization, @PathVariable Long id, @Valid @RequestBody EditaDadosDTO editaDadosDTO) {
        return ResponseEntity.status(200).body(usuarioService.editarDados(id, editaDadosDTO, authorization));
    }

    @PatchMapping("/trocar/senha/{id}")
    public ResponseEntity<RespostaDTO> trocarSenha(@RequestHeader String authorization, @PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO) {
        return ResponseEntity.status(200).body(usuarioService.trocarSenha(id, trocaSenhaDTO, authorization));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@RequestHeader String authorization, @PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id, @RequestHeader String authorization) {
        return ResponseEntity.status(200).body(usuarioService.buscarPorId(id, authorization));
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<UsuarioDTO>> buscarTodos(@RequestHeader String authorization) {
        return ResponseEntity.status(200).body(usuarioService.buscarTodos(authorization));
    }

}
