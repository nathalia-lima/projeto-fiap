package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarItemCardapioTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepositoryMock;

    @InjectMocks
    private BuscarItemCardapio buscarItemCardapioMock;


    @Test
    @DisplayName("Dado um input invalido ao Buscar por ID, deve lançar uma NotFoundException indicando que o item do cardapio não foi encontrado")
    void buscarPorIdErroIdNaoEncontrado() {

        NotFoundException notFoundException = new NotFoundException("Item do cardapio não encontrado - ID: 5");
        doThrow(notFoundException).when(itemCardapioRepositoryMock).findById(5L);

       var exception = assertThrows(NotFoundException.class, () -> buscarItemCardapioMock.buscarPorId(5l));

       assertEquals("Item do cardapio não encontrado - ID: 5", exception.getMessage());

      }

}