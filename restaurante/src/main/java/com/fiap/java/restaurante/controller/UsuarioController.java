package com.fiap.java.restaurante.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.fiap.java.restaurante.DTO.UsuarioDTO;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.fiap.java.restaurante.DTO.RespostaDTO;
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
    public ResponseEntity<RespostaDTO> salvar(@RequestBody @Valid UsuarioDTO user) {
        return ResponseEntity.CREATED().body(usuarioService.salvar(user));
    }

    @GetMapping("/login")
    public String login() {
        return "Área protegida!";
    }

    @PutMapping("/login/{id}")
    public String editar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO user) {
        usuarioService.editar(id, user);
        return "Atualização feita com sucesso";
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return "Exclusão feita com sucesso";
    }

}
