package com.fiap.java.restaurante.MockData;

import com.fiap.java.restaurante.domains.PerfilUsuario;


public class PerfilUsuarioMockData {


    public static String criarSalvarPerfilUsuarioInput() {
        return """
                {
                  "nome": "FUNCIONARIO"
                }""";
    }
    public static PerfilUsuario criarSalvarOuBuscarPerfilUsuarioOutput() {
        return new PerfilUsuario(4L, "FUNCIONARIO");
    }
}
