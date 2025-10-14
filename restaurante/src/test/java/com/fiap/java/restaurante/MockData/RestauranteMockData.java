package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.domains.Restaurante;

import static com.fiap.java.restaurante.MockData.EnderecoMockData.criarEndereco;

public class RestauranteMockData {

    public static String criarSalvarRestauranteInput() {
        return """
                {
                  "nome": "Restaurante Sabor & Arte",
                  "endereco": {
                    "cep": "20000-000",
                    "rua": "Av. Brasil",
                    "numero": "123",
                    "complemento": "Sala 3",
                    "bairro": "Centro",
                    "cidade": "Rio de Janeiro",
                    "estado": "RJ"
                  },
                  "tipoCozinha": "Italiana",
                  "horarioFuncionamento": "08:00-18:00",
                  "idDono": 2
                }""";
    }

    public static Restaurante criarSalvarOuBuscarRestauranteOutput() {
        return new Restaurante(1L, "Restaurante Sabor & Arte",
                criarEndereco(),
                "Italiana", "08:00-18:00", 2L);
    }

    public static String criarEditarRestauranteInput() {
        return """
                {
                  "nome": "Restaurante Sabor & Arte - Atualizado",
                  "endereco": {
                    "cep": "20000-000",
                    "rua": "Av. Brasil",
                    "numero": "123",
                    "complemento": "Sala 3",
                    "bairro": "Centro",
                    "cidade": "Rio de Janeiro",
                    "estado": "RJ"
                  },
                  "tipoCozinha": "Italiana",
                  "horarioFuncionamento": "08:00-18:00"
                }""";
    }

    public static Restaurante criarEditarRestauranteOutput() {
        return new Restaurante(1L, "Restaurante Sabor & Arte - Atualizado",
                criarEndereco(),
                "Italiana", "08:00-18:00", 2L);
    }




}
