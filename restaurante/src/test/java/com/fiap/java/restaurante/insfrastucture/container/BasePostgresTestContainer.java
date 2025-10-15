package com.fiap.java.restaurante.insfrastucture.container;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public class BasePostgresTestContainer {
    private static final PostgresTestContainer postgresTestContainer;
    static {
        postgresTestContainer = PostgresTestContainer.getInstance();
        postgresTestContainer.start();
    }

    @DynamicPropertySource
    static void setDynamicProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", postgresTestContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresTestContainer::getUsername);
        registry.add("spring.datasource.password", postgresTestContainer::getPassword);
    }
}
