package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
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

import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarSalvarItemCardapioInput;
import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarSalvaroOuBuscarItemCardapioOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarItemCardapioTest {

    @Mock
    private RuntimeException runtimeException;

    @Mock
    private RestauranteRepository restauranteRepository;

   @InjectMocks
    private CadastrarItemCardapio cadastrarItemCardapio;

    @Test
    @DisplayName("Dado um input invalido, deve lancar uma RuntimeException indicando que o restaurante nao foi encontrado")
    void salvarItemCardapioErroRestauranteNaoEncontrado() throws JsonProcessingException {

        var input = new ObjectMapper().readValue(criarSalvarItemCardapioInput(), ItemCardapioDTO.class);

        doThrow(runtimeException).when(restauranteRepository).findById(1L);
        when(runtimeException.getMessage()).thenReturn("Restaurante não encontrado");

        var exception = assertThrows(RuntimeException.class, () -> cadastrarItemCardapio.salvar(input));

        assertEquals("Restaurante não encontrado", exception.getMessage());

    }
}