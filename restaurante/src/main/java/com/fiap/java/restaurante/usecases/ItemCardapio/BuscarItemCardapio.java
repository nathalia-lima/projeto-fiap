package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarItemCardapio {
    private final ItemCardapioRepository itemCardapioRepository;
    private final RespostaMapper respostaMapper;

    public ItemCardapio buscarPorId(Long id) {
        return respostaMapper.itemCardapioEntitytoItemCardapio(itemCardapioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item do cardapio não encontrado - ID: " + id)));
    }
}
