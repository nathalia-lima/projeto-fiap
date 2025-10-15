package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.domains.Usuario;

import java.time.LocalDateTime;

import static com.fiap.java.restaurante.MockData.EnderecoMockData.criarEndereco;

public class UsuarioMockData {

    public static String criarSalvarUsuarioInput() {
        return """
                {
                  "nome": "João Silva",
                  "cpf": "123.456.789-02",
                  "email": "joao.silva@example.com",
                  "senha": "senha123",
                  "endereco": {
                    "cep": "01234-567",
                    "rua": "Rua Exemplo",
                    "numero": "123",
                    "complemento": "Apt 4",
                    "bairro": "Centro",
                    "cidade": "São Paulo",
                    "estado": "SP"
                  },
                  "usuario": 2
                }
                """;
    }

    public static Usuario criarSalvarouBuscarOuLoginUsuarioOutput() {
        return new Usuario(1L,
                "João Silva",
                "123.456.789-02",
                "joao.silva@example.com",
                "$2a$10$yWVK5Z5nOPWS/qwJPbnVTOebsQwL1Y.xYcxt/UDhpwtQTaF/YWrzu",
                criarEndereco(),
                2L,
                LocalDateTime.now());
    }

    public static String criarEditarUsuarioInput() {
        return """
                {
                   "email": "novoemail@example.com",
                   "endereco": {
                     "cep": "01234-567",
                     "rua": "Rua Nova",
                     "numero": "456",
                     "complemento": "Casa",
                     "bairro": "Bairro Novo",
                     "cidade": "Rio de Janeiro",
                     "estado": "RJ"
                   }
                 }
                """;
    }


    public static Usuario criarEditarUsuarioOutput() {
        return new Usuario(1L,
                "João Silva",
                "123.456.789-02",
                "novoemail@example.com",
                "$2a$10$yWVK5Z5nOPWS/qwJPbnVTOebsQwL1Y.xYcxt/UDhpwtQTaF/YWrzu",
                criarEndereco(),
                2L,
                LocalDateTime.now());
    }

    public static String criarLoginUsuarioInput() {
        return """
                {
                  "email": "joao.silva@example.com",
                  "senha": "senha123"
                }
                """;
    }

    public static String criarTrocarSenhaUsuarioInput() {
        return """
                {
                   "senhaAtual": "senha123",
                   "novaSenha": "novaSenha456",
                   "confirmaNovaSenha": "novaSenha456"
                 }
                """;
    }

    public static Usuario criarTrocarSenhaUsuarioOutput() {
        return new Usuario(1L,
                "João Silva",
                "123.456.789-02",
                "joao.silva@example.com",
                "$2a$10$Y.uDxH..YVrge2a0u5PRje4qqqJv.qM986OZ8KDgkFOi.HNNQBiuW",
                criarEndereco(),
                2L,
                LocalDateTime.now());
    }


}
