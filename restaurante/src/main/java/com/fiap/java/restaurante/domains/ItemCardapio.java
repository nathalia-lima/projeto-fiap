package com.fiap.java.restaurante.domains;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemCardapio {
    private Long id;
    private Long restauranteId;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
    private String foto;

    public ItemCardapio(Long restauranteId,String nome, String descricao, BigDecimal preco, Boolean disponivel, String foto) {
        this.restauranteId = restauranteId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.foto = foto;
    }

    public ItemCardapio(Long id, Long restauranteId, String nome, String descricao, BigDecimal preco, Boolean disponivel, String foto) {
        this.id = id;
        this.restauranteId = restauranteId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.foto = foto;
    }

    public void atualizarItemCardapio(String nome, String descricao, BigDecimal preco, Boolean disponivel, String foto){
        if(nome != null){
            this.nome = nome;
        }
        if(descricao != null){
            this.descricao = descricao;
        }
        if(preco != null){
            this.preco = preco;
        }
        if(disponivel != null){
            this.disponivel = disponivel;
        }
        if(foto != null){
            this.foto = foto;
        }
    }
}
