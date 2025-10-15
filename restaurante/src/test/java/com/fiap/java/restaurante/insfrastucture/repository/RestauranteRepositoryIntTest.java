package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.container.BasePostgresTestContainer;
import com.fiap.java.restaurante.insfrastucture.entity.EnderecoEntity;
import com.fiap.java.restaurante.insfrastucture.entity.PerfilUsuarioEntity;
import com.fiap.java.restaurante.insfrastucture.entity.RestauranteEntity;
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
public class RestauranteRepositoryIntTest extends BasePostgresTestContainer {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private RestauranteEntity novoRestaurante;

    @BeforeAll
    void setup() {
        EnderecoEntity endereco = new EnderecoEntity("00000-000", "Rua", "Bairro",
                "num","com", "cidade", "SP");

        UsuarioEntity dono = new UsuarioEntity();
        dono.setNome("Dono");
        dono.setEmail("dono@email");
        dono.setSenha("123456");
        dono.setCpf("11111111111");
        dono.setPerfilUsuarioEntity(new PerfilUsuarioEntity(2l, "DONO_RESTAURANTE"));
        dono.setEnderecoEntity(endereco);

        usuarioRepository.save(dono);

        novoRestaurante = new RestauranteEntity();
        novoRestaurante.setDono(dono);

        novoRestaurante.setNome("Restaurante");
        novoRestaurante.setHorarioFuncionamento("12:00:00");
        novoRestaurante.setTipoCozinha("Pizzaria");
        restauranteRepository.save(novoRestaurante);
    }

    @Test
    void deveRetornarRestauranteInserido() {
        Optional<RestauranteEntity> restaurante = restauranteRepository.findById(novoRestaurante.getId());

        assertTrue(restaurante.isPresent());

        assertEquals(novoRestaurante.getId(),  restaurante.get().getId());
    }

    @Test
    void deveRetornarNuloEmCasoDeIdInvalido() {
        Long id = 100L;
        Optional<RestauranteEntity> restaurante = restauranteRepository.findById(id);

        assertFalse(restaurante.isPresent());
    }

    @Test
    void deveOcorrerErroAoCadastrarRestauranteSemDone() {
        RestauranteEntity novoRestaurante2 = new RestauranteEntity();

        novoRestaurante2.setNome("Restaurante");
        novoRestaurante2.setHorarioFuncionamento("12:00:00");
        novoRestaurante2.setTipoCozinha("Pizzaria");

        assertThrows(DataIntegrityViolationException.class, () -> restauranteRepository.save(novoRestaurante2));
    }
}
