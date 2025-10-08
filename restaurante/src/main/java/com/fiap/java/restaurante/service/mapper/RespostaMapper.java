package com.fiap.java.restaurante.service.mapper;

import java.util.Optional;

import com.fiap.java.restaurante.DTO.*;
import com.fiap.java.restaurante.models.Endereco;
import com.fiap.java.restaurante.models.ItemCardapio;
import com.fiap.java.restaurante.models.Restaurante;
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
        usuarioDTO.setDataAlteracao(usuario.getDataAlteracao());
        return usuarioDTO;
    }

    public RespostaDTO mapUsuarioAtualizadoToRespostaDTO(Usuario usuario) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setCodigo("200");
        respostaDTO.setMensagem("Usuário atualizado com sucesso");
        return respostaDTO;
    }

    public RespostaDTO mapSenhaAtualizadaToRespostaDTO(Long id) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setCodigo("200");
        respostaDTO.setMensagem("Senha atualizada com sucesso");
        return respostaDTO;
    }

    public RespostaDTO mapUsuarioLoginToRespostaDTO(Usuario usuario) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setCodigo("200");
        respostaDTO.setMensagem(String.format("Usuário de nome %s logado com sucesso", usuario.getNome()));

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

    public Endereco mapEditaDadosEnderecoDTOToEndereco(EnderecoDTO enderecoDTO, Endereco enderecoExistente) {
       
        Endereco enderecoAtualizado = new Endereco();



        enderecoAtualizado.setCep(Optional.ofNullable(enderecoDTO.getCep()).orElse(enderecoExistente.getCep()));
        enderecoAtualizado.setRua(Optional.ofNullable(enderecoDTO.getRua()).orElse(enderecoExistente.getRua()));
        enderecoAtualizado.setNumero(Optional.ofNullable(enderecoDTO.getNumero()).orElse(enderecoExistente.getNumero()));
        enderecoAtualizado.setComplemento(Optional.ofNullable(enderecoDTO.getComplemento()).orElse(enderecoExistente.getComplemento()));
        enderecoAtualizado.setBairro(Optional.ofNullable(enderecoDTO.getBairro()).orElse(enderecoExistente.getBairro()));
        enderecoAtualizado.setCidade(Optional.ofNullable(enderecoDTO.getCidade()).orElse(enderecoExistente.getCidade()));
        enderecoAtualizado.setEstado(Optional.ofNullable(enderecoDTO.getEstado()).orElse(enderecoExistente.getEstado()));   
        return enderecoAtualizado;
    }

    public RestauranteDTO mapRestauranteToRestauranteDTO(Restaurante restaurante) {
        RestauranteDTO restauranteDTO = new RestauranteDTO();
        restauranteDTO.setId(restaurante.getId());
        restauranteDTO.setNome(restaurante.getNome());
        restauranteDTO.setTipoCozinha(restaurante.getTipoCozinha());
        restauranteDTO.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        restauranteDTO.setEndereco(mapEnderecoToEnderecoDTO(restaurante.getEndereco()));
        return restauranteDTO;
    }

    public RespostaDTO mapRestauranteAtualizadoToRespostaDTO(Long id) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setCodigo("200");
        respostaDTO.setMensagem("Restaurante atualizado com sucesso");
        return respostaDTO;
    }

    public ItemCardapioDTO mapItemCardapioToItemCardapioDTO(ItemCardapio itemCardapio) {
        ItemCardapioDTO itemCardapioDTO = new ItemCardapioDTO();
        itemCardapioDTO.setId(itemCardapio.getId());
        itemCardapioDTO.setNome(itemCardapio.getNome());
        itemCardapioDTO.setDescricao(itemCardapio.getDescricao());
        itemCardapioDTO.setPreco(itemCardapio.getPreco());
        itemCardapioDTO.setDisponivel(itemCardapio.isDisponivel());
        return itemCardapioDTO;
    }

    public RespostaDTO mapItemCardapioAtualizadoToRespostaDTO(Long id) {
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setCodigo("200");
        respostaDTO.setMensagem("Item do cardápio atualizado com sucesso");
        return respostaDTO;
    }
}