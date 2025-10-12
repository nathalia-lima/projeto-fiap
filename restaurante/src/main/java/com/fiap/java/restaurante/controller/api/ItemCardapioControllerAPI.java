package com.fiap.java.restaurante.controller.api;

import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.dto.ItemCardapioEditaDTO;
import org.springframework.http.ResponseEntity;

public interface ItemCardapioControllerAPI {
    public ResponseEntity<ItemCardapio> salvar(ItemCardapioDTO itemCardapioDTO);
    public ResponseEntity<ItemCardapio> editarDados(Long id, ItemCardapioEditaDTO itemCardapioDTO);
    public ResponseEntity<Void> excluir(Long id);
    public ResponseEntity<ItemCardapio> buscarPorId(Long id);
}
