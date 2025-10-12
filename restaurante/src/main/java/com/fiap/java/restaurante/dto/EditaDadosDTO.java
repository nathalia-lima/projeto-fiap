package com.fiap.java.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Getter;

@Valid
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditaDadosDTO {
        
        private String email;
        private EnderecoDTO endereco;
        private Long usuario;
}
