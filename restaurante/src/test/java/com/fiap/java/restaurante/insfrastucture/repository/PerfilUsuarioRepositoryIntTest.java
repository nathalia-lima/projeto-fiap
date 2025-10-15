package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.container.BasePostgresTestContainer;
import com.fiap.java.restaurante.insfrastucture.entity.PerfilUsuarioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PerfilUsuarioRepositoryIntTest extends BasePostgresTestContainer {
    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Test
    void deveRetornarOsPerfisClienteDonoRestauranteEAdministrador() {
        List<PerfilUsuarioEntity> perfis = perfilUsuarioRepository.findAll();

        Set<String> nomesPerfis = perfis.stream()
                .map(PerfilUsuarioEntity::getNome)
                .collect(Collectors.toSet());

        Set<String> perfisEsperados = Set.of("CLIENTE", "DONO_RESTAURANTE", "ADMINISTRADOR");

        assertTrue(nomesPerfis.containsAll(perfisEsperados));
    }
}
