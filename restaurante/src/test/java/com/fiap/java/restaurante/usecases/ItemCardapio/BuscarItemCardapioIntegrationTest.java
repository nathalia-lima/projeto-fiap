package com.fiap.java.restaurante.usecases.ItemCardapio;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BuscarItemCardapioIntegrationTest {

    @Autowired
    private BuscarItemCardapio buscarItemCardapio;


    @Test
    @DisplayName("Dado um input valido, deve buscar o item do cardapio com sucesso")
    void buscarPorIdComSucesso() {

       var itemCardapioResponse = buscarItemCardapio.buscarPorId(1L);

        assertNotNull(itemCardapioResponse);
        assertNotNull(itemCardapioResponse.getId());
        assertNotNull(itemCardapioResponse.getRestauranteId());
        assertNotNull(itemCardapioResponse.getNome());
        assertNotNull(itemCardapioResponse.getDescricao());
        assertNotNull(itemCardapioResponse.getPreco());
        assertNotNull(itemCardapioResponse.getDisponivel());
        assertNotNull(itemCardapioResponse.getFoto());


    }

}