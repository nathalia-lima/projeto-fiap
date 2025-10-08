package com.fiap.java.restaurante.controller.api;

import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.RestauranteDTO;
import com.fiap.java.restaurante.DTO.RestauranteEditaDTO;
import org.springframework.http.ResponseEntity;

public interface RestauranteControllerAPI {
    public ResponseEntity<RestauranteDTO> salvar(RestauranteDTO restauranteDTO);
    public ResponseEntity<RespostaDTO> editarDados(Long id, RestauranteEditaDTO restaurante);
    public ResponseEntity<Void> excluir(Long id);
    public ResponseEntity<RestauranteDTO> buscarPorId(Long id);
}
