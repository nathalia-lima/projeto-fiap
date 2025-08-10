package com.fiap.java.restaurante.service.mapper;

import com.fiap.java.restaurante.DTO.EnderecoDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.UsuarioDTO;
import com.fiap.java.restaurante.models.Endereco;
import com.fiap.java.restaurante.models.Usuario;

public class RespostaMapper {

    public UsuarioDTO mapUsuarioToUsuarioDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setEndereco(mapEnderecoToEnderecoDTO(usuario.getEndereco()));
        usuarioDTO.setUsuario(usuario.getPerfilUsuario());
        return usuarioDTO;
    }

    public RespostaDTO mapUsuarioAtualizadoToRespostaDTO(Usuario usuario) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Usuário atualizado com sucesso");
        return respostaDTO;
    }

    public RespostaDTO mapSenhaAtualizadaToRespostaDTO(Long id) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Senha atualizada com sucesso");
        return respostaDTO;
    }
    public Endereco mapEnderecoDTOToEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoDTO.getCep());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        return endereco;
    }

    public EnderecoDTO mapEnderecoToEnderecoDTO (Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        return enderecoDTO;
    }
}