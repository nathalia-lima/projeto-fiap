package com.fiap.java.restaurante.domains;

import com.fiap.java.restaurante.dto.EnderecoDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Usuario {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private EnderecoDTO endereco;
    private Long usuario;
    private LocalDateTime dataAlteracao;

    public Usuario(String nome, String cpf, String email, String senha, EnderecoDTO endereco, Long usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.usuario = usuario;
        this.dataAlteracao = LocalDateTime.now();
    }

    public void atualizarDados(String email, Long usuario, EnderecoDTO endereco) {
        if (email != null) {
            this.email = email;
        }
        if (usuario != null) {
            this.usuario = usuario;
        }
        if (endereco != null) {
            this.endereco = endereco;
        }
    }

    public void atualizarSenha(String novaSenha) {
        this.senha = novaSenha;
    }
}
