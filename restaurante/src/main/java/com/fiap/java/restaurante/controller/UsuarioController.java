package com.fiap.java.restaurante.controller;

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
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid CriaUsuarioDTO user) {
        return ResponseEntity.status(201).body(usuarioService.salvar(user));
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO login ) {
        return "Área protegida!";
    }

    @PatchMapping("/editar/dados/{id}")
    public ResponseEntity<RespostaDTO> editarDados(@PathVariable Long id, @Valid @RequestBody EditaDadosDTO user) {
        return ResponseEntity.status(200).body(usuarioService.editarDados(id, user));
    }

    @PatchMapping("/trocar/senha/{id}")
    public ResponseEntity<RespostaDTO> trocarSenha(@PathVariable Long id, @Valid @RequestBody TrocaSenhaDTO user) {
        return ResponseEntity.status(200).body(usuarioService.trocarSenha(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(usuarioService.buscarPorId(id));
    }

}
