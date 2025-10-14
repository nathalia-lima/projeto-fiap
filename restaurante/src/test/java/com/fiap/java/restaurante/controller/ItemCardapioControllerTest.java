package com.fiap.java.restaurante.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.dto.ItemCardapioDTO;
import com.fiap.java.restaurante.dto.ItemCardapioEditaDTO;
import com.fiap.java.restaurante.usecases.ItemCardapio.BuscarItemCardapio;
import com.fiap.java.restaurante.usecases.ItemCardapio.CadastrarItemCardapio;
import com.fiap.java.restaurante.usecases.ItemCardapio.EditarItemCardapio;
import com.fiap.java.restaurante.usecases.ItemCardapio.ExcluirItemCardapio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.java.restaurante.MockData.ItemCardapioMockData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ItemCardapioControllerTest {

    @Mock
    private CadastrarItemCardapio cadastrarItemCardapio;

    @Mock
    private BuscarItemCardapio buscarItemCardapio;

    @Mock
    private EditarItemCardapio editarItemCardapio;

    @Mock
    private ExcluirItemCardapio excluirItemCardapio;


    @InjectMocks
    private ItemCardapioController itemCardapioController;


    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("Dado um input válido, deve salvar o Item do Cardápio com sucesso")
    void salvarComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(itemCardapioController).build();

        var expectedResponse = criarSalvaroOuBuscarItemCardapioOutput();

        when(cadastrarItemCardapio.salvar(any(ItemCardapioDTO.class)))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(post("/cardapio")
                        .contentType("application/json")
                        .content(criarSalvarItemCardapioInput()))
                .andExpect(status().isCreated()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, ItemCardapioDTO.class);

        ItemCardapio itemCardapioReponse = new ItemCardapio(mockResponseAsObject.getId(),
                mockResponseAsObject.getRestauranteId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getDescricao(),
                mockResponseAsObject.getPreco(),
                mockResponseAsObject.getDisponivel(),
                mockResponseAsObject.getFoto());

        assertEquals(expectedResponse.getRestauranteId(), itemCardapioReponse.getRestauranteId());
        assertEquals(expectedResponse.getNome(), itemCardapioReponse.getNome());
        assertEquals(expectedResponse.getDescricao(), itemCardapioReponse.getDescricao());
        assertEquals(expectedResponse.getPreco(), itemCardapioReponse.getPreco());
        assertEquals(expectedResponse.getDisponivel(), itemCardapioReponse.getDisponivel());
        assertEquals(expectedResponse.getFoto(), itemCardapioReponse.getFoto());
    }

    @Test
    @DisplayName("Dado um input válido, deve editar o Item do Cardápio com sucesso")
    void editarDadosComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(itemCardapioController).build();

        objectMapper = new ObjectMapper();

        var expectedResponse = criarEditarItemCardapioOutput();

        when(editarItemCardapio.editarDados(anyLong(), any(ItemCardapioEditaDTO.class)))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(patch("/cardapio/editar/dados/1")
                        .contentType("application/json")
                        .content(criarEditarItemCardapioInput()))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, ItemCardapioDTO.class);

        ItemCardapio itemCardapioReponse = new ItemCardapio(mockResponseAsObject.getId(),
                mockResponseAsObject.getRestauranteId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getDescricao(),
                mockResponseAsObject.getPreco(),
                mockResponseAsObject.getDisponivel(),
                mockResponseAsObject.getFoto());

        assertEquals(expectedResponse.getRestauranteId(), itemCardapioReponse.getRestauranteId());
        assertEquals(expectedResponse.getNome(), itemCardapioReponse.getNome());
        assertEquals(expectedResponse.getDescricao(), itemCardapioReponse.getDescricao());
        assertEquals(expectedResponse.getPreco(), itemCardapioReponse.getPreco());
        assertEquals(expectedResponse.getDisponivel(), itemCardapioReponse.getDisponivel());
        assertEquals(expectedResponse.getFoto(), itemCardapioReponse.getFoto());

    }

    @Test
    @DisplayName("Dado um input válido, deve excluir o Item do Cardápio com sucesso")
    void excluirComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(itemCardapioController).build();

        mockMvc.perform(delete("/cardapio/1")
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(excluirItemCardapio, times(1)).excluir(anyLong());

    }

    @Test
    @DisplayName("Dado um input válido, deve encontrar o Item do Cardápio com sucesso")
    void buscarPorIdComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(itemCardapioController).build();

        objectMapper = new ObjectMapper();

        var expectedResponse = criarSalvaroOuBuscarItemCardapioOutput();

        when(buscarItemCardapio.buscarPorId(anyLong()))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(get("/cardapio/1"))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, ItemCardapioDTO.class);

        ItemCardapio itemCardapioReponse = new ItemCardapio(mockResponseAsObject.getId(),
                mockResponseAsObject.getRestauranteId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getDescricao(),
                mockResponseAsObject.getPreco(),
                mockResponseAsObject.getDisponivel(),
                mockResponseAsObject.getFoto());

        assertEquals(expectedResponse.getRestauranteId(), itemCardapioReponse.getRestauranteId());
        assertEquals(expectedResponse.getNome(), itemCardapioReponse.getNome());
        assertEquals(expectedResponse.getDescricao(), itemCardapioReponse.getDescricao());
        assertEquals(expectedResponse.getPreco(), itemCardapioReponse.getPreco());
        assertEquals(expectedResponse.getDisponivel(), itemCardapioReponse.getDisponivel());
        assertEquals(expectedResponse.getFoto(), itemCardapioReponse.getFoto());
    }
}