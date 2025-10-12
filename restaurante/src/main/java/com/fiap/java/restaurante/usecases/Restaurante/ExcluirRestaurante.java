package com.fiap.java.restaurante.usecases.Restaurante;

import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirRestaurante {
    private final RestauranteRepository restauranteRepository;
    private final RespostaMapper respostaMapper;
    public void excluir(Long id) {
        Restaurante restaurante = respostaMapper.restauranteEntityToRestaurante(restauranteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurante não encontrado - ID: " + id)));
        restauranteRepository.delete(respostaMapper.restauranteToRestauranteEntity(restaurante));
    }
}
