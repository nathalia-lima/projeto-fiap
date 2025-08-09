package com.fiap.java.restaurante.service;

import com.fiap.java.restaurante.DTO.EnderecoDTO;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import com.fiap.java.restaurante.DTO.UsuarioDTO;
import com.fiap.java.restaurante.models.Endereco;
import com.fiap.java.restaurante.models.Usuario;
import com.fiap.java.restaurante.repository.UsuarioRepository;
import com.fiap.java.restaurante.service.mapper.RespostaMapper;

import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private final RespostaMapper respostaMapper = new RespostaMapper();

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RespostaDTO salvar(@Valid UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfilUsuario(dto.getUsuario());

        List<Endereco> enderecos = dto.getEndereco().stream().map(e -> {
            Endereco endereco = new Endereco();
            endereco.setCEP(e.getCEP());
            endereco.setRua(e.getRua());
            endereco.setNumero(e.getNumero());
            endereco.setBairro(e.getBairro());
            endereco.setCidade(e.getCidade());
            endereco.setEstado(e.getEstado());

            endereco.setUsuario(usuario);
            return endereco;
        }).collect(Collectors.toList());

        usuario.setEndereco(enderecos);
        usuarioRepository.save(usuario);
        return respostaMapper.mapUsuarioCriadoToRespostaDTO(usuario);
    }

    public Usuario editar(Long id, @Valid UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) { usuarioExistente.setSenha(passwordEncoder.encode(dto.getSenha())); }
        usuarioExistente.setPerfilUsuario(dto.getUsuario());

        for (EnderecoDTO eDto : dto.getEndereco()) {
            if (eDto.getRua() != null) {
                usuarioExistente.getEndereco().stream()
                        .filter(e -> e.getRua().equals(eDto.getRua()))
                        .findFirst()
                        .ifPresent(e -> {
                            e.setCEP(eDto.getCEP());
                            e.setRua(eDto.getRua());
                            e.setNumero(eDto.getNumero());
                            e.setBairro(eDto.getBairro());
                            e.setCidade(eDto.getCidade());
                            e.setEstado(eDto.getEstado());
                        });
            } else {
                Endereco novoEndereco = new Endereco();
                novoEndereco.setCEP(eDto.getCEP());
                novoEndereco.setRua(eDto.getRua());
                novoEndereco.setNumero(eDto.getNumero());
                novoEndereco.setBairro(eDto.getBairro());
                novoEndereco.setCidade(eDto.getCidade());
                novoEndereco.setEstado(eDto.getEstado());
                novoEndereco.setUsuario(usuarioExistente);
                usuarioExistente.getEndereco().add(novoEndereco);
            }
        }
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }

    public void excluir(Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuarioExistente);
    }
}
