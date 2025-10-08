package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.RestauranteDTO;
import com.fiap.java.restaurante.DTO.RestauranteEditaDTO;
import com.fiap.java.restaurante.controller.api.RestauranteControllerAPI;
import com.fiap.java.restaurante.models.PerfilUsuario;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.repository.UsuarioRepository;
import com.fiap.java.restaurante.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController implements RestauranteControllerAPI {

    private final RestauranteService restauranteService;
    private final UsuarioRepository usuarioRepository;

    public RestauranteController(RestauranteService restauranteService, UsuarioRepository usuarioRepository) {
        this.restauranteService = restauranteService;
        this.usuarioRepository = usuarioRepository;
    }


    @PostMapping
    public ResponseEntity<RestauranteDTO> salvar(@RequestBody @Valid RestauranteDTO restauranteDTO) {

        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(restauranteDTO.getDonoRestaurante().getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getPerfilUsuario() != PerfilUsuario.RESTAURANTE) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.status(201).body(restauranteService.salvar(restauranteDTO, usuario));
    }

    @PatchMapping("/editar/dados/{id}")
    public ResponseEntity<RespostaDTO> editarDados(@PathVariable Long id, @Valid @RequestBody RestauranteEditaDTO restaurante) {
        return ResponseEntity.status(200).body(restauranteService.editarDados(id, restaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        restauranteService.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(restauranteService.buscarPorId(id));
    }
}