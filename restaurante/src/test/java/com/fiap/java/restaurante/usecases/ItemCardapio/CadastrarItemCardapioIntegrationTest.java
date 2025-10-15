package com.fiap.java.restaurante.usecases.ItemCardapio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.ItemCardapioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.RestauranteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarSalvarItemCardapioInput;
import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.criarSalvaroOuBuscarItemCardapioOutput;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)

class CadastrarItemCardapioIntegrationTest {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RespostaMapper respostaMapper;

    @Autowired
    private CadastrarItemCardapio cadastrarItemCardapio;

    @Test
    void salvarComSucesso() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        var input = objectMapper.readValue(criarSalvarItemCardapioInput(), ItemCardapioDTO.class);

        var expectedResponse = criarSalvaroOuBuscarItemCardapioOutput();

        var itemCardapioCadastrado = cadastrarItemCardapio.salvar(input);

        assertEquals(expectedResponse.getRestauranteId(), itemCardapioCadastrado.getRestauranteId());
        assertEquals(expectedResponse.getNome(), itemCardapioCadastrado.getNome());
        assertEquals(expectedResponse.getDescricao(), itemCardapioCadastrado.getDescricao());
        assertEquals(expectedResponse.getPreco(), itemCardapioCadastrado.getPreco());
        assertEquals(expectedResponse.getDisponivel(), itemCardapioCadastrado.getDisponivel());
        assertEquals(expectedResponse.getFoto(), itemCardapioCadastrado.getFoto());
    }
}