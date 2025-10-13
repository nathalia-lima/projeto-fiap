package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.controller.api.ItemCardapioControllerAPI;
import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.dto.ItemCardapioEditaDTO;
import com.fiap.java.restaurante.usecases.ItemCardapio.BuscarItemCardapio;
import com.fiap.java.restaurante.usecases.ItemCardapio.CadastrarItemCardapio;
import com.fiap.java.restaurante.usecases.ItemCardapio.EditarItemCardapio;
import com.fiap.java.restaurante.usecases.ItemCardapio.ExcluirItemCardapio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cardapio")
@RequiredArgsConstructor
public class ItemCardapioController implements ItemCardapioControllerAPI {

    private final CadastrarItemCardapio cadastrarItemCardapio;
    private final BuscarItemCardapio buscarItemCardapio;
    private final EditarItemCardapio editarItemCardapio;
    private final ExcluirItemCardapio excluirItemCardapio;

    @PostMapping
    public ResponseEntity<ItemCardapio> salvar(@RequestBody ItemCardapioDTO itemCardapioDTO) {
        return ResponseEntity.status(201).body(cadastrarItemCardapio.salvar(itemCardapioDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemCardapio> editarDados(@PathVariable Long id, ItemCardapioEditaDTO itemCardapioDTO) {
        return ResponseEntity.status(200).body(editarItemCardapio.editarDados(id, itemCardapioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(Long id) {
        excluirItemCardapio.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(buscarItemCardapio.buscarPorId(id));
    }
}
