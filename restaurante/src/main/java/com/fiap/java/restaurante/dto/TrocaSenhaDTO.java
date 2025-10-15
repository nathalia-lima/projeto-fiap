package com.fiap.java.restaurante.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TrocaSenhaDTO {

        @NotBlank
        private String senhaAtual;
        @NotBlank
        private String novaSenha;
        @NotBlank
        private String confirmaNovaSenha;
}
