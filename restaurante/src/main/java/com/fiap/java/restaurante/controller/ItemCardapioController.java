package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.DTO.ItemCardapioDTO;
import com.fiap.java.restaurante.DTO.ItemCardapioEditaDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.controller.api.ItemCardapioControllerAPI;
import com.fiap.java.restaurante.models.PerfilUsuario;
import com.fiap.java.restaurante.models.Restaurante;
import com.fiap.java.restaurante.repository.RestauranteRepository;
import com.fiap.java.restaurante.service.ItemCardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cardapio")
public class ItemCardapioController implements ItemCardapioControllerAPI {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }
    @PostMapping
    public ResponseEntity<ItemCardapioDTO> salvar(@RequestBody ItemCardapioDTO itemCardapioDTO) {

        return ResponseEntity.status(201).body(itemCardapioService.salvar(itemCardapioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDTO> editarDados(@PathVariable Long id, ItemCardapioEditaDTO itemCardapioDTO) {
        return ResponseEntity.status(200).body(itemCardapioService.editarDados(id, itemCardapioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(Long id) {
        itemCardapioService.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(itemCardapioService.buscarPorId(id));
    }
}
