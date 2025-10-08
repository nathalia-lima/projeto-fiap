package com.fiap.java.restaurante.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", referencedColumnName = "id")
    private Restaurante restaurante;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean disponivel;

    private String foto;

    public Boolean isDisponivel() {
        return this.disponivel;
    }
}
