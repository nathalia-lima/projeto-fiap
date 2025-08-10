package com.fiap.java.restaurante.DTO;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class TrocaSenhaDTO {

        @NotBlank
        private String senhaAtual;
        @NotBlank
        private String novaSenha;
        @NotBlank
        private String confirmaNovaSenha;
}
