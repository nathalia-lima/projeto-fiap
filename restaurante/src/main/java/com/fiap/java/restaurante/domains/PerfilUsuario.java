package com.fiap.java.restaurante.domains;

import lombok.Getter;

@Getter
public class PerfilUsuario {
    private Long id;
    private String nome;

    public PerfilUsuario(String nome) {
        this.nome = nome;
    }
}
