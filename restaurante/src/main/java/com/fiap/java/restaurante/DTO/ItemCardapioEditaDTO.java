package com.fiap.java.restaurante.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCardapioEditaDTO {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
    private String foto;
}
