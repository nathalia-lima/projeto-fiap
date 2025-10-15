package com.fiap.java.restaurante.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.dto.RestauranteDTO;
import com.fiap.java.restaurante.dto.RestauranteEditaDTO;
import com.fiap.java.restaurante.usecases.Restaurante.BuscarRestaurante;
import com.fiap.java.restaurante.usecases.Restaurante.CadastrarRestaurante;
import com.fiap.java.restaurante.usecases.Restaurante.EditarRestaurante;
import com.fiap.java.restaurante.usecases.Restaurante.ExcluirRestaurante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.java.restaurante.MockData.RestauranteMockData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @InjectMocks
    private RestauranteController restauranteController;

    @Mock
    private CadastrarRestaurante cadastrarRestaurante;

    @Mock
    private BuscarRestaurante buscarRestaurante;

    @Mock
    private EditarRestaurante editarRestaurante;

    @Mock
    private ExcluirRestaurante excluirRestaurante;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Dado um input válido, deve salvar o Restaurante com sucesso")
    void salvarComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();

        var expectedResponse = criarSalvarOuBuscarRestauranteOutput();

        when(cadastrarRestaurante.salvar(any(RestauranteDTO.class)))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(post("/restaurante")
                        .contentType("application/json")
                        .content(criarSalvarRestauranteInput()))
                .andExpect(status().isCreated()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, RestauranteDTO.class);

        Restaurante restauranteResponse = new Restaurante(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getTipoCozinha(),
                mockResponseAsObject.getHorarioFuncionamento(),
                mockResponseAsObject.getIdDono());

        assertEquals(expectedResponse.getId(), restauranteResponse.getId());
        assertEquals(expectedResponse.getNome(), restauranteResponse.getNome());
        assertEquals(expectedResponse.getEndereco().getCep(), restauranteResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), restauranteResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), restauranteResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), restauranteResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), restauranteResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), restauranteResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), restauranteResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getTipoCozinha(), restauranteResponse.getTipoCozinha());
        assertEquals(expectedResponse.getHorarioFuncionamento(), restauranteResponse.getHorarioFuncionamento());
        assertEquals(expectedResponse.getIdDono(), restauranteResponse.getIdDono());

    }

    @Test
    @DisplayName("Dado um input válido, deve editar o Restaurante com sucesso")
    void editarDadosComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();

        objectMapper = new ObjectMapper();

        var expectedResponse = criarEditarRestauranteOutput();

        when(editarRestaurante.editarDados(anyLong(), any(RestauranteEditaDTO.class)))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(patch("/restaurante/editar/dados/1")
                        .contentType("application/json")
                        .content(criarEditarRestauranteInput()))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, RestauranteDTO.class);

        Restaurante restauranteResponse = new Restaurante(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getTipoCozinha(),
                mockResponseAsObject.getHorarioFuncionamento(),
                mockResponseAsObject.getIdDono());

        assertEquals(expectedResponse.getId(), restauranteResponse.getId());
        assertEquals(expectedResponse.getNome(), restauranteResponse.getNome());
        assertEquals(expectedResponse.getEndereco().getCep(), restauranteResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), restauranteResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), restauranteResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), restauranteResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), restauranteResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), restauranteResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), restauranteResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getTipoCozinha(), restauranteResponse.getTipoCozinha());
        assertEquals(expectedResponse.getHorarioFuncionamento(), restauranteResponse.getHorarioFuncionamento());
        assertEquals(expectedResponse.getIdDono(), restauranteResponse.getIdDono());

    }

    @Test
    @DisplayName("Dado um input válido, deve excluir o Restaurante com sucesso")
    void excluirComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();

        mockMvc.perform(delete("/restaurante/1")
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(excluirRestaurante, times(1)).excluir(anyLong());

    }

    @Test
    @DisplayName("Dado um input válido, deve encontrar o Restaurante com sucesso")
    void buscarPorIdComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(restauranteController).build();

        objectMapper = new ObjectMapper();

        var expectedResponse = criarSalvarOuBuscarRestauranteOutput();

        when(buscarRestaurante.buscarPorId(anyLong()))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(get("/restaurante/1"))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, RestauranteDTO.class);

        Restaurante restauranteResponse = new Restaurante(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getTipoCozinha(),
                mockResponseAsObject.getHorarioFuncionamento(),
                mockResponseAsObject.getIdDono());

        assertEquals(expectedResponse.getId(), restauranteResponse.getId());
        assertEquals(expectedResponse.getNome(), restauranteResponse.getNome());
        assertEquals(expectedResponse.getEndereco().getCep(), restauranteResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), restauranteResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), restauranteResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), restauranteResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), restauranteResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), restauranteResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), restauranteResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getTipoCozinha(), restauranteResponse.getTipoCozinha());
        assertEquals(expectedResponse.getHorarioFuncionamento(), restauranteResponse.getHorarioFuncionamento());
        assertEquals(expectedResponse.getIdDono(), restauranteResponse.getIdDono());
    }
}