package com.fiap.java.restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestauranteApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestauranteApplication.class, args);
	}

}
