package com.fiap.java.restaurante.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.CriaUsuarioDTO;
import com.fiap.java.restaurante.dto.EditaDadosDTO;
import com.fiap.java.restaurante.dto.LoginDTO;
import com.fiap.java.restaurante.dto.TrocaSenhaDTO;
import com.fiap.java.restaurante.usecases.Usuario.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.java.restaurante.MockData.UsuarioMockData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private CadastrarUsuario cadastrarUsuario;

    @Mock
    private EditarUsuario editarUsuario;

    @Mock
    private BuscarUsuario buscarUsuario;

    @Mock
    private ExcluirUsuario excluirUsuario;

    @Mock
    private LoginUsuario loginUsuario;

    @Mock
    private MudarSenhaUsuario trocarSenhaUsuario;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Dado um input válido, deve salvar o Usuario com sucesso")
    void salvarComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        var expectedResponse = criarSalvarouBuscarOuLoginUsuarioOutput();
        when(cadastrarUsuario.salvar(any(CriaUsuarioDTO.class))).thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(post("/usuario")
                .contentType("application/json")
                .content(criarSalvarUsuarioInput()))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        var mockReponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockReponseAsString, CriaUsuarioDTO.class);

        Usuario usuarioResponse = new Usuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getCpf(),
                mockResponseAsObject.getEmail(),
                mockResponseAsObject.getSenha(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getUsuario(),
                expectedResponse.getDataAlteracao());

        assertEquals(expectedResponse.getId(), usuarioResponse.getId());
        assertEquals(expectedResponse.getNome(), usuarioResponse.getNome());
        assertEquals(expectedResponse.getCpf(), usuarioResponse.getCpf());
        assertEquals(expectedResponse.getEmail(), usuarioResponse.getEmail());
        assertEquals(expectedResponse.getSenha(), usuarioResponse.getSenha());
        assertEquals(expectedResponse.getEndereco().getCep(), usuarioResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), usuarioResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), usuarioResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), usuarioResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), usuarioResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), usuarioResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), usuarioResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getUsuario(), usuarioResponse.getUsuario());
        assertNotNull(usuarioResponse.getDataAlteracao());
    }

    @Test
    @DisplayName("Dado um input válido, deve realizar o login do Usuario com sucesso")
    void loginComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        var expectedResponse = criarSalvarouBuscarOuLoginUsuarioOutput();

        when(loginUsuario.login(any(LoginDTO.class))).thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(post("/usuario/login")
                        .contentType("application/json")
                        .content(criarLoginUsuarioInput()))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        var mockReponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockReponseAsString, CriaUsuarioDTO.class);

        Usuario usuarioResponse = new Usuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getCpf(),
                mockResponseAsObject.getEmail(),
                mockResponseAsObject.getSenha(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getUsuario(),
                expectedResponse.getDataAlteracao());

        assertEquals(expectedResponse.getId(), usuarioResponse.getId());
        assertEquals(expectedResponse.getNome(), usuarioResponse.getNome());
        assertEquals(expectedResponse.getCpf(), usuarioResponse.getCpf());
        assertEquals(expectedResponse.getEmail(), usuarioResponse.getEmail());
        assertEquals(expectedResponse.getSenha(), usuarioResponse.getSenha());
        assertEquals(expectedResponse.getEndereco().getCep(), usuarioResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), usuarioResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), usuarioResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), usuarioResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), usuarioResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), usuarioResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), usuarioResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getUsuario(), usuarioResponse.getUsuario());
        assertNotNull(usuarioResponse.getDataAlteracao());
    }

    @Test
    @DisplayName("Dado um input válido, deve alterar o Usuario com sucesso")
    void editarDadosComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        var expectedResponse = criarEditarUsuarioOutput();
        when(editarUsuario.editarDados(anyLong(),any(EditaDadosDTO.class))).thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(MockMvcRequestBuilders.patch("/usuario/editar/dados/1")
                        .contentType("application/json")
                        .content(criarEditarUsuarioInput()))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockReponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockReponseAsString, CriaUsuarioDTO.class);

        Usuario usuarioResponse = new Usuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getCpf(),
                mockResponseAsObject.getEmail(),
                mockResponseAsObject.getSenha(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getUsuario(),
                expectedResponse.getDataAlteracao());

        assertEquals(expectedResponse.getId(), usuarioResponse.getId());
        assertEquals(expectedResponse.getNome(), usuarioResponse.getNome());
        assertEquals(expectedResponse.getCpf(), usuarioResponse.getCpf());
        assertEquals(expectedResponse.getEmail(), usuarioResponse.getEmail());
        assertEquals(expectedResponse.getSenha(), usuarioResponse.getSenha());
        assertEquals(expectedResponse.getEndereco().getCep(), usuarioResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), usuarioResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), usuarioResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), usuarioResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), usuarioResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), usuarioResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), usuarioResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getUsuario(), usuarioResponse.getUsuario());
        assertNotNull(usuarioResponse.getDataAlteracao());
    }

    @Test
    @DisplayName("Dado um input válido, deve trocar a senha do Usuario com sucesso")
    void trocarSenhaComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        var expectedResponse = criarTrocarSenhaUsuarioOutput();

        when(trocarSenhaUsuario.trocarSenha(anyLong(),any(TrocaSenhaDTO.class))).thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(MockMvcRequestBuilders.patch("/usuario/trocar/senha/1")
                        .contentType("application/json")
                        .content(criarTrocarSenhaUsuarioInput()))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockReponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockReponseAsString, CriaUsuarioDTO.class);

        Usuario usuarioResponse = new Usuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getCpf(),
                mockResponseAsObject.getEmail(),
                mockResponseAsObject.getSenha(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getUsuario(),
                expectedResponse.getDataAlteracao());

        assertEquals(expectedResponse.getId(), usuarioResponse.getId());
        assertEquals(expectedResponse.getNome(), usuarioResponse.getNome());
        assertEquals(expectedResponse.getCpf(), usuarioResponse.getCpf());
        assertEquals(expectedResponse.getEmail(), usuarioResponse.getEmail());
        assertEquals(expectedResponse.getSenha(), usuarioResponse.getSenha());
        assertEquals(expectedResponse.getEndereco().getCep(), usuarioResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), usuarioResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), usuarioResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), usuarioResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), usuarioResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), usuarioResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), usuarioResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getUsuario(), usuarioResponse.getUsuario());
        assertNotNull(usuarioResponse.getDataAlteracao());
    }

    @Test
    @DisplayName("Dado um input válido, deve excluir o Usuario com sucesso")
    void excluirComSucesso() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        mockMvc.perform(delete("/usuario/1")
                        .contentType("application/json"))
                .andExpect(status().isNoContent());

        verify(excluirUsuario, times(1)).excluir(anyLong());
    }

    @Test
    @DisplayName("Dado um input válido, deve encontrar o Usuario com sucesso")
    void buscarPorIdComSucesso() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        var expectedResponse = criarSalvarouBuscarOuLoginUsuarioOutput();
        when(buscarUsuario.buscarPorId(1L)).thenReturn(expectedResponse);

        var mockResponse = mockMvc.perform(get("/usuario/1"))
                .andExpect(status().isOk()).andReturn().getResponse();

        var mockReponseAsString = mockResponse.getContentAsString();
        var mockResponseAsObject = objectMapper.readValue(mockReponseAsString, CriaUsuarioDTO.class);

        Usuario usuarioResponse = new Usuario(mockResponseAsObject.getId(),
                mockResponseAsObject.getNome(),
                mockResponseAsObject.getCpf(),
                mockResponseAsObject.getEmail(),
                mockResponseAsObject.getSenha(),
                mockResponseAsObject.getEndereco(),
                mockResponseAsObject.getUsuario(),
                expectedResponse.getDataAlteracao());

        assertEquals(expectedResponse.getId(), usuarioResponse.getId());
        assertEquals(expectedResponse.getNome(), usuarioResponse.getNome());
        assertEquals(expectedResponse.getCpf(), usuarioResponse.getCpf());
        assertEquals(expectedResponse.getEmail(), usuarioResponse.getEmail());
        assertEquals(expectedResponse.getSenha(), usuarioResponse.getSenha());
        assertEquals(expectedResponse.getEndereco().getCep(), usuarioResponse.getEndereco().getCep());
        assertEquals(expectedResponse.getEndereco().getRua(), usuarioResponse.getEndereco().getRua());
        assertEquals(expectedResponse.getEndereco().getNumero(), usuarioResponse.getEndereco().getNumero());
        assertEquals(expectedResponse.getEndereco().getBairro(), usuarioResponse.getEndereco().getBairro());
        assertEquals(expectedResponse.getEndereco().getComplemento(), usuarioResponse.getEndereco().getComplemento());
        assertEquals(expectedResponse.getEndereco().getCidade(), usuarioResponse.getEndereco().getCidade());
        assertEquals(expectedResponse.getEndereco().getEstado(), usuarioResponse.getEndereco().getEstado());
        assertEquals(expectedResponse.getUsuario(), usuarioResponse.getUsuario());
        assertNotNull(usuarioResponse.getDataAlteracao());
    }
}