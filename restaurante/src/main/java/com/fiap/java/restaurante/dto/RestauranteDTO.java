package com.fiap.java.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class RestauranteDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Valid
    private EnderecoDTO endereco;
    @NotBlank
    private String tipoCozinha;
    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$", message = "O campo Horário de funcionamento deve estar no formato HH:mm-HH:mm (Exemplo: 08:00-18:00)")
    private String horarioFuncionamento;
    @NotNull
    private Long idDono;

}