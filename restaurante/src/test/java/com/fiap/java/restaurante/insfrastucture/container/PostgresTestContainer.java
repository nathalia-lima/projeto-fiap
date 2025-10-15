package com.fiap.java.restaurante.insfrastucture.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {
    private static final String POSTGRES_IMAGE = "postgres:16.8-alpine3.20";
    private static  PostgresTestContainer container;
    private PostgresTestContainer() {
        super(POSTGRES_IMAGE);
    }

    public static PostgresTestContainer getInstance() {
        if(container==null){
            container = new PostgresTestContainer();
        }

        return container;
    }
}
