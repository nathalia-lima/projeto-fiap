package com.fiap.java.restaurante.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnderecoDTO{
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
        private String cep;
        @NotBlank
        private String rua;
        @NotBlank
        private String numero;
        @NotBlank
        private String bairro;
        @NotBlank
        private String cidade;
        @NotBlank
        @Size(min = 2, max = 2, message = "Escreva somente a sigla do estado!")
        private String estado;

        public String getCEP() {
                return cep;
        }

        public String getRua() {
                return rua;
        }

        public String getNumero() {
                return numero;
        }

        public String getBairro() {
                return bairro;
        }

        public String getCidade() {
                return cidade;
        }

        public String getEstado() {
                return estado;
        }
}
