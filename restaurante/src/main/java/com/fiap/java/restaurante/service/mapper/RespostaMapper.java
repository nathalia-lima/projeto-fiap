package com.fiap.java.restaurante.service.mapper;

import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.models.Usuario;

public class RespostaMapper {

    public RespostaDTO mapUsuarioCriadoToRespostaDTO(Usuario usuario){
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setCodigo("201");
        respostaDTO.setMensagem("Usuário criado com sucesso - ID: " + usuario.getId());
        return respostaDTO;
    }
}