package com.fiap.java.restaurante.usecases.Usuario;

import com.fiap.java.restaurante.domains.PerfilUsuario;
import com.fiap.java.restaurante.domains.Usuario;
import com.fiap.java.restaurante.dto.CriaUsuarioDTO;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.insfrastucture.mapper.RespostaMapper;
import com.fiap.java.restaurante.insfrastucture.repository.PerfilUsuarioRepository;
import com.fiap.java.restaurante.insfrastucture.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarUsuario {
    private final UsuarioRepository usuarioRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RespostaMapper respostaMapper;

    public Usuario salvar(CriaUsuarioDTO criaUsuarioDTO) {
        PerfilUsuario perfilUsuario = validarPerfil(criaUsuarioDTO.getUsuario());

        Usuario usuario = new Usuario(
                criaUsuarioDTO.getNome(),
                criaUsuarioDTO.getCpf(),
                criaUsuarioDTO.getEmail(),
                passwordEncoder.encode(criaUsuarioDTO.getSenha()),
                criaUsuarioDTO.getEndereco(),
                perfilUsuario.getId()
        );

        return respostaMapper.usuarioEntityToUsuario(
                usuarioRepository.save(respostaMapper.usuarioToUsuarioEntity(usuario)));
    }

    private PerfilUsuario validarPerfil(Long idPerfil) {
        return respostaMapper.perfilUsuarioEntityToPerfilUsuario(perfilUsuarioRepository.findById(idPerfil)
                .orElseThrow(() -> new NotFoundException("Item do cardapio não encontrado - ID: " + idPerfil)));
    }
}
