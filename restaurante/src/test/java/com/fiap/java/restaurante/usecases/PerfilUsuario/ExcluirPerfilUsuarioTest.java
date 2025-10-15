package com.fiap.java.restaurante.usecases.PerfilUsuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ExcluirPerfilUsuarioTest {

    @InjectMocks
    private ExcluirPerfilUsuario excluirPerfilUsuario;

    @Mock
    private PerfilUsuarioRepository perfilUsuarioRepository;


    @Test
    @DisplayName("Dado um input invalido para excluir, deve lançar uma NotFoundException indicando que o perfil usuario não foi encontrado")
    void excluirPerfilUsuariiErroIdNaoEncontrado() throws JsonProcessingException {


        NotFoundException notFoundException = new NotFoundException("Perfil do Usuario não encontrado - ID: 5");
        doThrow(notFoundException).when(perfilUsuarioRepository).findById(5L);

        var exception = assertThrows(NotFoundException.class, () -> excluirPerfilUsuario.excluir(5L));

        assertEquals("Perfil do Usuario não encontrado - ID: 5", exception.getMessage());

    }
}