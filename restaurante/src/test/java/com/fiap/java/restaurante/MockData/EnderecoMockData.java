package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.dto.EnderecoDTO;
import com.fiap.java.restaurante.insfrastucture.entity.EnderecoEntity;

public class EnderecoMockData {

    public static EnderecoDTO criarEndereco() {
        return new EnderecoDTO("20000-000", "Av. Brasil", "123", "Sala 3", "Centro", "Rio de Janeiro", "RJ");
    }

    public static EnderecoEntity criarEnderecoEntity() {
        return new EnderecoEntity("20000-000", "Av. Brasil", "123", "Sala 3", "Centro", "Rio de Janeiro", "RJ");
    }


}
