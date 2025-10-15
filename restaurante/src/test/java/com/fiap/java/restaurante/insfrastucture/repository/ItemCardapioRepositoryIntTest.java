package com.fiap.java.restaurante.insfrastucture.repository;

import com.fiap.java.restaurante.insfrastucture.container.BasePostgresTestContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemCardapioRepositoryIntTest extends BasePostgresTestContainer {
    @Autowired
    private ItemCardapioRepository itemCardapioRepository;
}
