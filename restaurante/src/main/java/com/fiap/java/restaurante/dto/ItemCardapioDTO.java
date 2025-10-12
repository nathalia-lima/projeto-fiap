package com.fiap.java.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Valid
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCardapioDTO {
    private Long id;
    @NotNull
    private Long restauranteId;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero.")
    @Column(precision = 10, scale = 2)
    private BigDecimal preco;
    @NotNull
    private Boolean disponivel;
    private String foto;

}