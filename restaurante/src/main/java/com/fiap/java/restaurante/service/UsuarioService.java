package com.fiap.java.restaurante.service;

import com.fiap.java.restaurante.DTO.*;
import com.fiap.java.restaurante.exceptions.UnauthorizedException;
import com.fiap.java.restaurante.models.Endereco;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.repository.UsuarioRepository;
import com.fiap.java.restaurante.service.mapper.RespostaMapper;
import com.fiap.java.restaurante.exceptions.BadRequestException;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;


import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.fiap.java.restaurante.security.JwtUtil;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private final RespostaMapper respostaMapper = new RespostaMapper();
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO salvar(@Valid CriaUsuarioDTO dto, String authorization) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfilUsuario(dto.getUsuario());
        Endereco endereco = respostaMapper.mapEnderecoDTOToEndereco(dto.getEndereco());
        endereco.setUsuario(usuario);
        usuario.setEndereco(endereco);
        usuario.setDataAlteracao(LocalDateTime.now());
        usuarioRepository.save(usuario);                              
        return respostaMapper.mapUsuarioToUsuarioDTO(usuario);
    }

    public RespostaDTO editarDados(Long id, @Valid EditaDadosDTO dto, String authorization) {

        if (!jwtUtil.isTokenValid(authorization)) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }

         Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + id));

        //Campos sem alteração
        usuario.setNome(usuario.getNome());
        usuario.setCpf(usuario.getCpf());
        usuario.setSenha(usuario.getSenha());

        //Campos com possível alteração
        if (dto.getEmail() != null) {
        usuario.setEmail(dto.getEmail());
        }else {
            usuario.setEmail(usuario.getEmail());
        }
        if (dto.getEndereco() != null) {
            Endereco endereco = respostaMapper.mapEditaDadosEnderecoDTOToEndereco(dto.getEndereco());
            endereco.setUsuario(usuario);
            usuario.setEndereco(endereco);
        }else {
            usuario.setEndereco(usuario.getEndereco());
        }
        usuario.setDataAlteracao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return respostaMapper.mapUsuarioAtualizadoToRespostaDTO(usuario);
    }

    public RespostaDTO trocarSenha(Long id, @Valid TrocaSenhaDTO dto, String authorization) {

        if (!jwtUtil.isTokenValid(authorization)) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + id));

        // Campo de senha a ser alterado
        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new BadRequestException("Senha atual incorreta");
        }

        if (!dto.getNovaSenha().equals(dto.getConfirmaNovaSenha())) {
            throw new BadRequestException("Nova senha e confirmação não coincidem");
        }
        
        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));

        // Campos sem alteração
        usuario.setNome(usuario.getNome());
        usuario.setCpf(usuario.getCpf());
        usuario.setEmail(usuario.getEmail());
        usuario.setEndereco(usuario.getEndereco());
        
        usuario.setDataAlteracao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return respostaMapper.mapSenhaAtualizadaToRespostaDTO(id);
    }

    public void excluir(Long id, String authorization) {
         if (!jwtUtil.isTokenValid(authorization)) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }
            Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        
        if (jwtUtil.getRole().equals("CLIENTE") && usuario.getEmail().equals(jwtUtil.getUsername()) || jwtUtil.getRole().equals("RESTAURANTE")) {
            usuarioRepository.delete(usuario);
        } else {
            throw new UnauthorizedException("Usuário não autorizado a editar este perfil");
        }
    }

    public UsuarioDTO buscarPorId(Long id, String authorization) {
         if (!jwtUtil.isTokenValid(authorization)) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }

         if (jwtUtil.getRole().equals("CLIENTE") && usuario.getEmail().equals(jwtUtil.getUsername()) ||jwtUtil.getRole().equals("RESTAURANTE")) {
            Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado - ID: " + id));
        } else {
            throw new UnauthorizedException("Usuário não autorizado a editar este perfil");
        }

        
        return respostaMapper.mapUsuarioToUsuarioDTO(usuario);
    }

    public List<UsuarioDTO> buscarTodos() {
        if (!jwtUtil.isTokenValid(authorization)) {
            throw new UnauthorizedException("Token inválido ou expirado");
        }
        if (!jwtUtil.getRole().equals("RESTAURANTE")) {
             List<Usuario> usuarios = usuarioRepository.findAll();
          }  else{
            throw new UnauthorizedException("Usuário não autorizado a editar este perfil");
        }

       
        return respostaMapper.mapUsuariosToUsuarioDTOs(usuarios);
    }

    public RespostaDTO login(LoginDTO loginDTO) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmailIgnoreCase(loginDTO.getEmail());

        

        if(optUsuario.isEmpty() || !passwordEncoder.matches(loginDTO.getSenha(), optUsuario.get().getSenha())) {
            throw new UnauthorizedException("Login e/ou senha inválidos");
        }

        String token = jwtUtil.generateToken(optUsuario.get().getEmail(), optUsuario.get().getPerfilUsuario());

        return respostaMapper.mapUsuarioLoginToRespostaDTO(optUsuario.get(), token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return usuarioRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }
                                        
}

