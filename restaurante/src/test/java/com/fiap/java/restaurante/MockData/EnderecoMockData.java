package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.dto.EnderecoDTO;

public class EnderecoMockData {

    public static EnderecoDTO criarEndereco() {
        return new EnderecoDTO("20000-000", "Av. Brasil", "123", "Sala 3", "Centro", "Rio de Janeiro", "RJ");
    }


}
