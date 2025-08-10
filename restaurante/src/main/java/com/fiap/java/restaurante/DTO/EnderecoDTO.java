package com.fiap.java.restaurante.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO{
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
        private String cep;
        @NotBlank
        private String rua;
        @NotBlank
        private String numero;
        private String complemento;
        @NotBlank
        private String bairro;
        @NotBlank
        private String cidade;
        @NotBlank
        @Size(min = 2, max = 2, message = "Escreva somente a sigla do estado!")
        private String estado;

}
