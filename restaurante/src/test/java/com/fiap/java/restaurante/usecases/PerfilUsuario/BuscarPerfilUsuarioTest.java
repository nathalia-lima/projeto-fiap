package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class BuscarPerfilUsuarioTest {

    @InjectMocks
    private BuscarPerfilUsuario buscarPerfilUsuario;

    @Mock
    private PerfilUsuarioRepository perfilUsuarioRepositoryMock;

    @Test
    @DisplayName("Dado um input invalido ao Buscar por ID, deve lançar uma NotFoundException indicando que o perfil do usuario não foi encontrado")
    void buscarPorIdErroPerfilNaoEncontrado() {

        NotFoundException notFoundException = new NotFoundException("Perfil Usuario não encontrado - ID: 5");
        doThrow(notFoundException).when(perfilUsuarioRepositoryMock).findById(5L);

        var exception = assertThrows(NotFoundException.class, () -> buscarPerfilUsuario.buscarPorId(5L));

        assertEquals("Perfil Usuario não encontrado - ID: 5", exception.getMessage());
    }
}