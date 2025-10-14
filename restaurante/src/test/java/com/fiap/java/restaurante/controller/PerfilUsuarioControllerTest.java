package com.fiap.java.restaurante.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.dto.PerfilUsuarioDTO;
import com.fiap.java.restaurante.usecases.PerfilUsuario.BuscarPerfilUsuario;
import com.fiap.java.restaurante.usecases.PerfilUsuario.CadastrarPerfilUsuario;
import com.fiap.java.restaurante.usecases.PerfilUsuario.ExcluirPerfilUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.java.restaurante.MockData.PerfilUsuarioMockData.criarSalvarOuBuscarPerfilUsuarioOutput;
import static com.fiap.java.restaurante.MockData.PerfilUsuarioMockData.criarSalvarPerfilUsuarioInput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PerfilUsuarioControllerTest {

    @InjectMocks
    private PerfilUsuarioController perfilUsuarioController;

    @Mock
    private CadastrarPerfilUsuario cadastrarPerfilUsuario;

    @Mock
    private BuscarPerfilUsuario buscarPerfilUsuario;

    @Mock
    private ExcluirPerfilUsuario excluirPerfilUsuario;

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Dado um input válido, deve salvar o Pefil do Usuario com sucesso")
    void salvarComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(perfilUsuarioController).build();

        var expectedResponse = criarSalvarOuBuscarPerfilUsuarioOutput();

        when(cadastrarPerfilUsuario.salvar(any(PerfilUsuarioDTO.class)))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(post("/perfil")
                        .contentType("application/json")
                    .content(criarSalvarPerfilUsuarioInput()))
                .andExpect(status().isCreated()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, PerfilUsuarioDTO.class);

        PerfilUsuario perfilUsuario = new PerfilUsuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome());

        assertEquals(expectedResponse.getNome(), perfilUsuario.getNome());
        assertEquals(expectedResponse.getId(), perfilUsuario.getId());

    }

    @Test
    @DisplayName("Dado um input válido, deve excluir o Pefil do Usuario com sucesso")
    void excluirComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(perfilUsuarioController).build();

        mockMvc.perform(delete("/perfil/1")
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(excluirPerfilUsuario, times(1)).excluir(anyLong());

    }

    @Test
    @DisplayName("Dado um input válido, deve encontrar o Pefil do Usuario com sucesso")
    void buscarPorIdComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(perfilUsuarioController).build();

        objectMapper = new ObjectMapper();

        var expectedResponse = criarSalvarOuBuscarPerfilUsuarioOutput();

        when(buscarPerfilUsuario.buscarPorId(anyLong()))
                .thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(get("/perfil/1"))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockResponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockResponseAsString, PerfilUsuarioDTO.class);

        PerfilUsuario perfilUsuario = new PerfilUsuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome());

        assertEquals(expectedResponse.getNome(), perfilUsuario.getNome());
        assertEquals(expectedResponse.getId(), perfilUsuario.getId());
    }
}