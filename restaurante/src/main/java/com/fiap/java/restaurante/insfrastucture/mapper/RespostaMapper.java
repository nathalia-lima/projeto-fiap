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
                entity.getId(),
                entity.getRestaurante().getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivel(),
                entity.getFoto()
        );
        return domain;
    }

    public ItemCardapioEntity itemCardapioToItemCardapioEntity(ItemCardapio domain, Restaurante restaurante) {
        ItemCardapioEntity entity = new ItemCardapioEntity(
                domain.getId(),
                domain.getNome(),
                domain.getDescricao(),
                domain.getPreco(),
                domain.getDisponivel(),
                domain.getFoto(),
                restauranteToRestauranteEntity(restaurante)
        );
        return entity;
    }

    public EnderecoDTO enderecoEntityToEnderecoDTO(EnderecoEntity enderecoEntity) {
        return new EnderecoDTO(
                enderecoEntity.getCep(),
                enderecoEntity.getRua(),
                enderecoEntity.getNumero(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getBairro(),
                enderecoEntity.getCidade(),
                enderecoEntity.getEstado()
        );
    }

    public PerfilUsuarioEntity perfilUsuariotoPerfilUsuarioEntity(PerfilUsuario perfilUsuario) {
        return new PerfilUsuarioEntity(
                perfilUsuario.getId(),
                perfilUsuario.getNome()
        );
    }

    public PerfilUsuario perfilUsuarioEntityToPerfilUsuario(PerfilUsuarioEntity perfilUsuarioEntity) {
        return new PerfilUsuario(
                perfilUsuarioEntity.getId(),
                perfilUsuarioEntity.getNome()
        );
    }

    public Restaurante restauranteEntityToRestaurante(RestauranteEntity restaurante) {
        return new Restaurante(
                restaurante.getId(),
                restaurante.getNome(),
                enderecoEntityToEnderecoDTO(restaurante.getEnderecoEntity()),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDono().getId()
        );
    }

    public RestauranteEntity restauranteToRestauranteEntity (Restaurante restaurante) {
        return new RestauranteEntity(
                restaurante.getId(),
                restaurante.getNome(),
                new EnderecoEntity(
                        restaurante.getEndereco().getCep(),
                        restaurante.getEndereco().getRua(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getComplemento(),
                        restaurante.getEndereco().getBairro(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado()
                ),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                null,
                new UsuarioEntity(restaurante.getIdDono())
        );
    }

    public Usuario usuarioEntityToUsuario (UsuarioEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getCpf(),
                usuarioEntity.getEmail(),
                usuarioEntity.getSenha(),
                enderecoEntityToEnderecoDTO(usuarioEntity.getEnderecoEntity()),
                usuarioEntity.getPerfilUsuarioEntity().getId(),
                usuarioEntity.getDataAlteracao()
        );
    }

    public UsuarioEntity usuarioToUsuarioEntity (Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDataAlteracao(),
                new EnderecoEntity(
                        usuario.getEndereco().getCep(),
                        usuario.getEndereco().getRua(),
                        usuario.getEndereco().getNumero(),
                        usuario.getEndereco().getComplemento(),
                        usuario.getEndereco().getBairro(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getEstado()
                ),
                new PerfilUsuarioEntity(usuario.getUsuario())
        );
    }
}
