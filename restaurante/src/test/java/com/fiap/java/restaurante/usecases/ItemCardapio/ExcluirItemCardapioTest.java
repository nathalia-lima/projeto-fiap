package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.java.restaurante.dto.ItemCardapioEditaDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarEditarItemCardapioEntityOutput;
import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarEditarItemCardapioInput;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirItemCardapioTest {

    @InjectMocks
    private ExcluirItemCardapio excluirItemCardapio;

    @Mock
    private  ItemCardapioRepository itemCardapioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;


    @Test
    @DisplayName("Dado um input invalido para excluir, deve lançar uma NotFoundException indicando que o item do cardapio não foi encontrado")
    void excluirItemCardapioErroIdNaoEncontrado() throws JsonProcessingException {


        NotFoundException notFoundException = new NotFoundException("Item do cardapio não encontrado - ID: 5");
        doThrow(notFoundException).when(itemCardapioRepository).findById(5L);

        var exception = assertThrows(NotFoundException.class, () -> excluirItemCardapio.excluir(5L));

        assertEquals("Item do cardapio não encontrado - ID: 5", exception.getMessage());

    }

    @Test
    @DisplayName("Dado um input invalido para excluir, deve lançar uma NotFoundException indicando que o restaurante vinculado não foi encontrado")
    void excluirItemCardapioErroRestauranteNaoEncontrado() throws JsonProcessingException {

        NotFoundException notFoundException = new NotFoundException("Restaurante não encontrado para o item do cardápio - ID: 1");
        doThrow(notFoundException).when(restauranteRepository).findById(1L);
        when(itemCardapioRepository.findById(1L)).thenReturn(Optional.of(criarEditarItemCardapioEntityOutput()));


        var exception = assertThrows(NotFoundException.class, () -> excluirItemCardapio.excluir(1L));

        assertEquals("Restaurante não encontrado para o item do cardápio - ID: 1", exception.getMessage());

    }
}