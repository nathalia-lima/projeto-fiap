package com.fiap.java.restaurante.DTO;
import com.fiap.java.restaurante.models.PerfilUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UsuarioDTO {
        @NotBlank
        private String nome;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String senha;
        @NotNull
        @Valid
        private List<EnderecoDTO> endereco;
        @NotNull
        private PerfilUsuario usuario;

        public String getNome() {
                return nome;
        }
        public String getEmail() {
                return email;
        }

        public String getSenha() {
                return senha;
        }

        public List<EnderecoDTO> getEndereco() {
                return endereco;
        }

        public PerfilUsuario getUsuario() {
                return usuario;
        }
}
