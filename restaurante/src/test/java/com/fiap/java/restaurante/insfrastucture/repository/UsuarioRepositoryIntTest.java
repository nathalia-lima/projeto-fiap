package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.container.BasePostgresTestContainer;
import com.fiap.java.restaurante.insfrastucture.entity.EnderecoEntity;
import com.fiap.java.restaurante.insfrastucture.entity.PerfilUsuarioEntity;
import com.fiap.java.restaurante.insfrastucture.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryIntTest extends BasePostgresTestContainer {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioEntity novoUsuario;


    @BeforeAll()
    void setup() {
        EnderecoEntity endereco = new EnderecoEntity("00000-000", "Rua", "Bairro",
                "num","com", "cidade", "SP");

        novoUsuario = new UsuarioEntity();
        novoUsuario.setNome("Fulano");
        novoUsuario.setCpf("222222222");
        novoUsuario.setEmail("fulano@email");
        novoUsuario.setSenha("123456");
        novoUsuario.setEnderecoEntity(endereco);


        PerfilUsuarioEntity perfil = new PerfilUsuarioEntity(1L, "CLIENTE");
        perfil.setId(1L);

        novoUsuario.setPerfilUsuarioEntity(perfil);

        usuarioRepository.save(novoUsuario);
    }

    @Test
    void deveRetornarUsuarioInserido(){

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(novoUsuario.getId());

        assertTrue(usuario.isPresent());

        assertEquals(novoUsuario.getId(),  usuario.get().getId());
    }


    @Test
    void deveRetonarUsuarioPorEmail() {
        String email = "fulAno@eMail";
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmailIgnoreCase(email);

        assertTrue(usuario.isPresent());

        assertTrue(email.equalsIgnoreCase(usuario.get().getEmail()));
    }

    @Test
    void deveRetornarNuloEmCasoDeIdInvalido() {
        Long id = 100L;
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);

        assertFalse(usuario.isPresent());
    }

    @Test
    void deveRetornarNuloEmCasoDeEmailInvalido() {
        String email = "fulAno@gmail";
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmailIgnoreCase(email);

        assertFalse(usuario.isPresent());
    }

    @Test
    void deveOcorrerErroAoSalvarSemPerfil() {
        EnderecoEntity endereco = new EnderecoEntity("00000-000", "Rua", "Bairro",
                "num","com", "cidade", "SP");

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Fulano2");
        usuario.setCpf("333333333");
        usuario.setEmail("fulano2@email");
        usuario.setSenha("123456");
        usuario.setEnderecoEntity(endereco);

        assertThrows(DataIntegrityViolationException.class, () -> usuarioRepository.save(usuario));
    }

    @Test
    void deveOcorrerErroAoSalvarComEmailDuplicado() {
        EnderecoEntity endereco = new EnderecoEntity("00000-000", "Rua", "Bairro",
                "num","com", "cidade", "SP");

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Fulana");
        usuario.setCpf("34594858392");
        usuario.setEmail("fulano@email");
        usuario.setSenha("123456");
        usuario.setEnderecoEntity(endereco);

        assertThrows(DataIntegrityViolationException.class, () -> usuarioRepository.save(usuario));
    }

    @Test
    void deveOcorrerErroAoSalvarComCpfDuplicado() {
        EnderecoEntity endereco = new EnderecoEntity("00000-000", "Rua", "Bairro",
                "num","com", "cidade", "SP");

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Fulana2");
        usuario.setCpf("222222222");
        usuario.setEmail("fulana2@email");
        usuario.setSenha("123456");
        usuario.setEnderecoEntity(endereco);

        assertThrows(DataIntegrityViolationException.class, () -> usuarioRepository.save(usuario));
    }

}
