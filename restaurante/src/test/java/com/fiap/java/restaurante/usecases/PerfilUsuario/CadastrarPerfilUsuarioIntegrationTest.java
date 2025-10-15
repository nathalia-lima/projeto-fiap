package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.dto.PerfilUsuarioDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarSalvarItemCardapioInput;
import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarSalvaroOuBuscarItemCardapioOutput;
import static com.fiap.java.restaurante.MockData.PerfilUsuarioMockData.criarSalvarOuBuscarPerfilUsuarioOutput;
import static com.fiap.java.restaurante.MockData.PerfilUsuarioMockData.criarSalvarPerfilUsuarioInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CadastrarPerfilUsuarioIntegrationTest {

    @Autowired
    CadastrarPerfilUsuario cadastrarPerfilUsuario;

    @Test
    void salvarComSucesso() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        var input = objectMapper.readValue(criarSalvarPerfilUsuarioInput(), PerfilUsuarioDTO.class);

        var expectedResponse = criarSalvarOuBuscarPerfilUsuarioOutput();

        var perfilUsuarioCadastrado = cadastrarPerfilUsuario.salvar(input);

        assertEquals(expectedResponse.getId(), perfilUsuarioCadastrado.getId());
        assertEquals(expectedResponse.getNome(), perfilUsuarioCadastrado.getNome());
    }
}