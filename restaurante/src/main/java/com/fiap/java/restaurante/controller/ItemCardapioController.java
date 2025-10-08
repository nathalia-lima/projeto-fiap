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
    private final RestauranteRepository restauranteRepository;

    public ItemCardapioController(ItemCardapioService itemCardapioService, RestauranteRepository restauranteRepository) {
        this.itemCardapioService = itemCardapioService;
        this.restauranteRepository = restauranteRepository;
    }
    @PostMapping("/itemcardapio")
    public ResponseEntity<ItemCardapioDTO> salvar(ItemCardapioDTO itemCardapioDTO) {

        Restaurante restaurante = restauranteRepository.findById(itemCardapioDTO.getId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        if (restaurante.getDonoRestaurante().getPerfilUsuario() != PerfilUsuario.RESTAURANTE) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.status(201).body(itemCardapioService.salvar(itemCardapioDTO, restaurante));
    }

    @PatchMapping("/editar/dados/{id}")
    public ResponseEntity<RespostaDTO> editarDados(Long id, ItemCardapioEditaDTO itemCardapioDTO) {
        return ResponseEntity.status(200).body(itemCardapioService.editarDados(id, itemCardapioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(Long id) {
        itemCardapioService.excluir(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioDTO> buscarPorId(Long id) {
        return ResponseEntity.status(200).body(itemCardapioService.buscarPorId(id));
    }
}
