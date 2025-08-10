package com.fiap.java.restaurante.service;

import com.fiap.java.restaurante.DTO.EditaDadosDTO;
import com.fiap.java.restaurante.DTO.EnderecoDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.TrocaSenhaDTO;
import com.fiap.java.restaurante.DTO.UsuarioDTO;
import com.fiap.java.restaurante.models.Endereco;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.repository.UsuarioRepository;
import com.fiap.java.restaurante.service.mapper.RespostaMapper;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.exceptions.BadRequestException;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private final RespostaMapper respostaMapper = new RespostaMapper();

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO salvar(@Valid UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfilUsuario(dto.getUsuario());
        usuario.setEndereco(respostaMapper.mapEnderecoDTOToEndereco(dto.getEndereco()));
        usuarioRepository.save(usuario);
        return respostaMapper.mapUsuarioToUsuarioDTO(usuario);
    }

    public RespostaDTO editarDados(Long id, @Valid EditaDadosDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado - ID: " + id));
        usuario.setEmail(dto.getEmail());
        usuario.setEndereco(respostaMapper.mapEnderecoDTOToEndereco(dto.getEndereco()));
        usuario.setDataAlteracao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return respostaMapper.mapUsuarioAtualizadoToRespostaDTO(usuario);
    }

    public RespostaDTO trocarSenha(Long id, @Valid TrocaSenhaDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + id));
        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new BadRequestException("Senha atual incorreta");
        }
        if (!dto.getNovaSenha().equals(dto.getConfirmaNovaSenha())) {
            throw new BadRequestException("Nova senha e confirmação não coincidem");
        }
        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        usuario.setDataAlteracao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return respostaMapper.mapSenhaAtualizadaToRespostaDTO(id);
    }

    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + id));
        return respostaMapper.mapUsuarioToUsuarioDTO(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return usuarioRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }
                                        
}

