package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fiap.java.restaurante.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
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
class ExcluirPerfilUsuarioIntegrationTest {

    @Autowired
    ExcluirPerfilUsuario excluirPerfilUsuario;

    @Test
    @DisplayName("Dado um input valido ao tentar excluir uma segunda vez, deve lançar NotFoundException indicando que o perfil do usuario foi excluido com sucesso")
    void excluirComSucesso() {

        excluirPerfilUsuario.excluir(1L);

        var exception = assertThrows(NotFoundException.class, () -> excluirPerfilUsuario.excluir(1L));

        assertEquals("Perfil usuario não encontrado - ID: 1", exception.getMessage());

    }
}