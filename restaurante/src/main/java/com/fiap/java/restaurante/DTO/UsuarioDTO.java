package com.fiap.java.restaurante.DTO;
import com.fiap.java.restaurante.models.PerfilUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {
        private Long id;
        @NotBlank
        private String nome;
        @NotBlank
        @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$", message = "CPF deve conter 11 dígitos e pode ou não conter pontos e traços")
        private String cpf;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String senha;
        @NotNull
        @Valid
        private EnderecoDTO endereco;
        @NotNull
        private PerfilUsuario usuario;
        private LocalDateTime dataAlteracao;
}