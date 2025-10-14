package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.domains.ItemCardapio;

public class ItemCardapioMockData {

    public static String criarSalvarItemCardapioInput(){
        return "{\n" +
                "  \"restauranteId\": 1,\n" +
                "  \"nome\": \"Pizza Margherita\",\n" +
                "  \"descricao\": \"Pizza tradicional com tomate, mussarela e manjericão.\",\n" +
                "  \"preco\": 39.90,\n" +
                "  \"disponivel\": true,\n" +
                "  \"foto\": \"https://exemplo.com/imagens/pizza-margherita.jpg\"\n" +
                "}";
    }

    public static String criarEditarItemCardapioInput(){
        return "{\n" +
                "  \"nome\": \"Pizza Pepperoni\",\n" +
                "  \"descricao\": \"Pizza com pepperoni, mussarela e molho de tomate.\",\n" +
                "  \"preco\": 45.50,\n" +
                "  \"disponivel\": true,\n" +
                "  \"foto\": \"https://exemplo.com/imagens/pizza-pepperoni.jpg\"\n" +
                "}";
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
