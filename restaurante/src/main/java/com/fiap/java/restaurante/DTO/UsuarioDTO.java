package com.fiap.java.restaurante.DTO;
import com.fiap.java.restaurante.models.PerfilUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
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

}
