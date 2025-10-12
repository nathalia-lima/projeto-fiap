package com.fiap.java.restaurante.insfrastucture.mapper;

import com.fiap.java.restaurante.domains.ItemCardapio;
import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.domains.Restaurante;
import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.insfrastucture.entity.*;
import com.fiap.java.restaurante.dto.EnderecoDTO;
import org.springframework.stereotype.Component;

@Component
public class RespostaMapper {
    public static ItemCardapio itemCardapioEntitytoItemCardapio(ItemCardapioEntity entity) {
        ItemCardapio domain = new ItemCardapio(
                entity.getRestaurante().getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivel(),
                entity.getFoto()
        );
        return domain;
    }

    public static ItemCardapioEntity itemCardapioToItemCardapioEntity(ItemCardapio domain) {
        ItemCardapioEntity entity = new ItemCardapioEntity(
                domain.getNome(),
                domain.getDescricao(),
                domain.getPreco(),
                domain.getDisponivel(),
                domain.getFoto(),
                new RestauranteEntity(domain.getRestauranteId())
        );
        return entity;
    }

    public EnderecoDTO enderecoEntityToEnderecoDTO(EnderecoEntity enderecoEntity) {
        return new EnderecoDTO(
                enderecoEntity.getRua(),
                enderecoEntity.getNumero(),
                enderecoEntity.getCidade(),
                enderecoEntity.getEstado(),
                enderecoEntity.getCep()
        );
    }

    public PerfilUsuarioEntity perfilUsuariotoPerfilUsuarioEntity(PerfilUsuario perfilUsuario) {
        return new PerfilUsuarioEntity(
                perfilUsuario.getNome()
        );
    }

    public PerfilUsuario perfilUsuarioEntityToPerfilUsuario(PerfilUsuarioEntity perfilUsuarioEntity) {
        return new PerfilUsuario(
                perfilUsuarioEntity.getNome()
        );
    }

    public Restaurante restauranteEntityToRestaurante(RestauranteEntity restaurante) {
        return new Restaurante(
                restaurante.getNome(),
                enderecoEntityToEnderecoDTO(restaurante.getEnderecoEntity()),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDono().getId()
        );
    }

    public RestauranteEntity restauranteToRestauranteEntity (Restaurante restaurante) {
        return new RestauranteEntity(
                restaurante.getNome(),
                new EnderecoEntity(
                        restaurante.getEndereco().getRua(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado(),
                        restaurante.getEndereco().getCep()
                ),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getIdDono()
        );
    }

    public Usuario usuarioEntityToUsuario (UsuarioEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getNome(),
                usuarioEntity.getCpf(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                enderecoEntityToEnderecoDTO(usuarioEntity.getEnderecoEntity()),
                usuarioEntity.getPerfilUsuarioEntity().getId()
        );
    }

    public UsuarioEntity usuarioToUsuarioEntity (Usuario usuario) {
        return new UsuarioEntity(
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                new EnderecoEntity(
                        usuario.getEndereco().getRua(),
                        usuario.getEndereco().getNumero(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getEstado(),
                        usuario.getEndereco().getCep()
                ),
                usuario.getUsuario()
        );
    }
}
