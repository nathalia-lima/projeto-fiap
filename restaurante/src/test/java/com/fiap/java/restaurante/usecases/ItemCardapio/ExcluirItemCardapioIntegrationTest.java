package com.fiap.java.restaurante.usecases.ItemCardapio;


import com.fiap.java.restaurante.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ExcluirItemCardapioIntegrationTest {

    @Autowired
    private ExcluirItemCardapio excluirItemCardapio;

    @Test
    void excluirComSucesso() {

        excluirItemCardapio.excluir(1L);

        var exception = assertThrows(NotFoundException.class, () -> excluirItemCardapio.excluir(5L));

        assertEquals("Item do cardapio não encontrado - ID: 5", exception.getMessage());

    }
}
