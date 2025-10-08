package com.fiap.java.restaurante.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Valid
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestauranteDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Valid
    private EnderecoDTO endereco;
    @NotBlank
    private String tipoCozinha;
    @NotBlank
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$", message = "O campo Horário de funcionamento deve estar no formato HH:mm-HH:mm (Exemplo: 08:00-18:00)")
    private String horarioFuncionamento;
    @NotBlank
    @Valid
    private UsuarioDTO donoRestaurante;

}