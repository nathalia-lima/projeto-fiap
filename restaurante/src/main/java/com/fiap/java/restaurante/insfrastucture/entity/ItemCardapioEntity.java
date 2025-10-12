package com.fiap.java.restaurante.insfrastucture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "item_cardapio")
@NoArgsConstructor
public class ItemCardapioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean disponivel;

    private String foto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restaurante;

    public ItemCardapioEntity(String nome, String descricao, BigDecimal preco, Boolean disponivel, String foto, RestauranteEntity restaurante) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.foto = foto;
        this.restaurante = restaurante;
    }

    public Boolean isDisponivel() {
        return this.disponivel;
    }
}
