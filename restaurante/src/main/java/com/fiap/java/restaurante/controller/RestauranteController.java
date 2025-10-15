package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.controller.api.RestauranteControllerAPI;
import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.dto.RestauranteDTO;
import com.fiap.java.restaurante.dto.RestauranteEditaDTO;
import com.fiap.java.restaurante.usecases.Restaurante.BuscarRestaurante;
import com.fiap.java.restaurante.usecases.Restaurante.CadastrarRestaurante;
import com.fiap.java.restaurante.usecases.Restaurante.EditarRestaurante;
import com.fiap.java.restaurante.usecases.Restaurante.ExcluirRestaurante;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante")
@RequiredArgsConstructor
public class RestauranteController implements RestauranteControllerAPI {

    private final CadastrarRestaurante cadastrarRestaurante;
    private final BuscarRestaurante buscarRestaurante;
    private final EditarRestaurante editarRestaurante;
    private final ExcluirRestaurante excluirRestaurante;

    @PostMapping
    public ResponseEntity<Restaurante> salvar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        return ResponseEntity.status(201).body(cadastrarRestaurante.salvar(restauranteDTO));
    }

    @PatchMapping("/editar/dados/{id}")
    public ResponseEntity<Restaurante> editarDados(@PathVariable Long id, @Valid @RequestBody RestauranteEditaDTO restaurante) {
        return ResponseEntity.status(200).body(editarRestaurante.editarDados(id, restaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirRestaurante.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(buscarRestaurante.buscarPorId(id));
    }
}