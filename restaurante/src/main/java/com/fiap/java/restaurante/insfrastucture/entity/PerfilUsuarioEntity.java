package com.fiap.java.restaurante.insfrastucture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "perfil_usuario")
public class PerfilUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    public PerfilUsuarioEntity(String nome) {
    }
}
