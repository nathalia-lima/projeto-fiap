package com.fiap.java.restaurante.controller.api;

import com.fiap.java.restaurante.DTO.ItemCardapioDTO;
import com.fiap.java.restaurante.DTO.ItemCardapioEditaDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import org.springframework.http.ResponseEntity;

public interface ItemCardapioControllerAPI {
    public ResponseEntity<ItemCardapioDTO> salvar(ItemCardapioDTO itemCardapioDTO);
    public ResponseEntity<RespostaDTO> editarDados(Long id, ItemCardapioEditaDTO itemCardapioDTO);
    public ResponseEntity<Void> excluir(Long id);
    public ResponseEntity<ItemCardapioDTO> buscarPorId(Long id);
}
