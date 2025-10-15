package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.dto.ItemCardapioEditaDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarEditarItemCardapioInput;
import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarEditarItemCardapioOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EditarItemCardapioIntegrationTest {

    @Autowired
    private EditarItemCardapio editarItemCardapio;

    @Test
    void editarDadosComSucesso() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        var input = objectMapper.readValue(criarEditarItemCardapioInput(), ItemCardapioEditaDTO.class);

        var expectedResponse = criarEditarItemCardapioOutput();

        var itemCardapioAlterado = editarItemCardapio.editarDados(1L,input);

        assertEquals(expectedResponse.getRestauranteId(), itemCardapioAlterado.getRestauranteId());
        assertEquals(expectedResponse.getNome(), itemCardapioAlterado.getNome());
        assertEquals(expectedResponse.getDescricao(), itemCardapioAlterado.getDescricao());
        assertEquals(expectedResponse.getPreco(), itemCardapioAlterado.getPreco());
        assertEquals(expectedResponse.getDisponivel(), itemCardapioAlterado.getDisponivel());
        assertEquals(expectedResponse.getFoto(), itemCardapioAlterado.getFoto());
    }
}