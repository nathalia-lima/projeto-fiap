package com.fiap.java.restaurante.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Valid
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private LocalTime horarioFuncionamento;
    @NotNull
    private Long idDono;

}