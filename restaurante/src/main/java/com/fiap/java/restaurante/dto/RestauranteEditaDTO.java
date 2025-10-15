package com.fiap.java.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Valid
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestauranteEditaDTO {

    private String nome;
    private EnderecoDTO endereco;
    private String tipoCozinha;
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d-([01]\\d|2[0-3]):[0-5]\\d$", message = "O campo Horário de funcionamento deve estar no formato HH:mm-HH:mm (Exemplo: 08:00-18:00)")
    private String horarioFuncionamento;
}
