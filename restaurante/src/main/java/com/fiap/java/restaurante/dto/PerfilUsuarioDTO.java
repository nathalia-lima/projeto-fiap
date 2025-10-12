package com.fiap.java.restaurante.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PerfilUsuarioDTO {
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[A-Z]+$", message = "O tipo de perfil deve conter apenas letras maiúsculas e sem espaços")
    private String nome;
}
