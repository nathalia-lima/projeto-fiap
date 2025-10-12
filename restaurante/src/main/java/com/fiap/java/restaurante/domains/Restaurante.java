package com.fiap.java.restaurante.domains;

import com.fiap.java.restaurante.dto.EnderecoDTO;
import lombok.Getter;

@Getter
public class Restaurante {
    private Long id;
    private String nome;
    private EnderecoDTO endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Long idDono;

    public Restaurante( String nome, EnderecoDTO endereco, String tipoCozinha, String horarioFuncionamento, Long idDono) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.idDono = idDono;
    }

    public void atualizarDados(String nome, String tipoCozinha, String horarioFuncionamento, EnderecoDTO endereco) {
        if (nome != null) {
            this.nome = nome;
        }
        if (tipoCozinha != null) {
            this.tipoCozinha = tipoCozinha;
        }
        if (horarioFuncionamento != null) {
            this.horarioFuncionamento = horarioFuncionamento;
        }
        if (endereco != null) {
            this.endereco = endereco;
        }
    }
}
