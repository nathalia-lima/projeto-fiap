package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.DTO.UsuarioDTO;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public String salvar(@RequestBody @Valid UsuarioDTO user) {
        Usuario usuarioSalvo = usuarioService.salvar(user);
        return "Usuário cadastrado com sucesso! ID: " + usuarioSalvo.getId();
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

    @DeleteMapping
    public String excluir(@PathVariable Long id, @Valid @RequestBody UsuarioDTO user) {
        usuarioService.excluir(id, user);
        return "Exclusão feita com sucesso";
    }

}
