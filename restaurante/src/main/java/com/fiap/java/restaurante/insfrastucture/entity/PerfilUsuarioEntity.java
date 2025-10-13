package com.fiap.java.restaurante.insfrastucture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfil_usuario")
public class PerfilUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    public PerfilUsuarioEntity(String nome) {
        this.nome = nome;
    }

    public PerfilUsuarioEntity(Long usuario) {
        this.id = usuario;
    }
}
