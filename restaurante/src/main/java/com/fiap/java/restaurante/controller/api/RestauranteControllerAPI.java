package com.fiap.java.restaurante.controller.api;

import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.dto.RestauranteDTO;
import com.fiap.java.restaurante.dto.RestauranteEditaDTO;
import org.springframework.http.ResponseEntity;

public interface RestauranteControllerAPI {
    public ResponseEntity<Restaurante> salvar(RestauranteDTO restauranteDTO);
    public ResponseEntity<Restaurante> editarDados(Long id, RestauranteEditaDTO restaurante);
    public ResponseEntity<Void> excluir(Long id);
    public ResponseEntity<Restaurante> buscarPorId(Long id);
}
