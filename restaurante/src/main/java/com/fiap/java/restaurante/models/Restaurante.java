package com.fiap.java.restaurante.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    private String tipoCozinha;

    private String horarioFuncionamento;

    @OneToOne
    @JoinColumn(name = "dono_restaurante", referencedColumnName = "id")
    private Usuario donoRestaurante;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ItemCardapio> itensCardapio;

}
