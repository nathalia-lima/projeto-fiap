package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirItemCardapio {
    private final ItemCardapioRepository itemCardapioRepository;
    private final RestauranteRepository restauranteRepository;
    private final RespostaMapper respostaMapper;

    public void excluir(Long id) {
        ItemCardapio itemCardapio = respostaMapper.itemCardapioEntitytoItemCardapio(
                itemCardapioRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Item do cardapio não encontrado - ID: " + id))
        );

        Restaurante restaurante = restauranteRepository.findById(itemCardapio.getRestauranteId())
                .map(respostaMapper::restauranteEntityToRestaurante)
                .orElseThrow(() -> new NotFoundException("Restaurante não encontrado para o item do cardápio - ID: " + itemCardapio.getRestauranteId()));

        itemCardapioRepository.delete(respostaMapper.itemCardapioToItemCardapioEntity(itemCardapio, restaurante));
    }
}
