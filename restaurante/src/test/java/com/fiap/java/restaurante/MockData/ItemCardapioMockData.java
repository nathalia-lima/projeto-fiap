package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.domains.ItemCardapio;

public class ItemCardapioMockData {

    public static String criarSalvarItemCardapioInput(){
        return """
                {
                  "restauranteId": 1,
                  "nome": "Pizza Margherita",
                  "descricao": "Pizza tradicional com tomate, mussarela e manjericão.",
                  "preco": 39.90,
                  "disponivel": true,
                  "foto": "https://exemplo.com/imagens/pizza-margherita.jpg"
                }""";
    }

    public static String criarEditarItemCardapioInput(){
        return """
                {
                  "nome": "Pizza Pepperoni",
                  "descricao": "Pizza com pepperoni, mussarela e molho de tomate.",
                  "preco": 45.50,
                  "disponivel": true,
                  "foto": "https://exemplo.com/imagens/pizza-pepperoni.jpg"
                }""";
    }

    public static ItemCardapio criarSalvaroOuBuscarItemCardapioOutput(){
        return new ItemCardapio(
                1L,
                1L,
                "Pizza Margherita",
                "Pizza tradicional com tomate, mussarela e manjericão.",
                new java.math.BigDecimal("39.90"),
                true,
                "https://exemplo.com/imagens/pizza-margherita.jpg"
        );
    }

    public static ItemCardapio criarEditarItemCardapioOutput(){
        return new ItemCardapio(
                1L,
                1L,
                "Pizza Pepperoni",
                "Pizza com pepperoni, mussarela e molho de tomate.",
                new java.math.BigDecimal("45.50"),
                true,
                "https://exemplo.com/imagens/pizza-pepperoni.jpg"
        );

    }





}
