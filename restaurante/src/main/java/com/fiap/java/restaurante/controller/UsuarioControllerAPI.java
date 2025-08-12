package com.fiap.java.restaurante.controller;

import com.fiap.java.restaurante.DTO.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UsuarioControllerAPI {

    @Operation(description = "Cadastro de usuários", responses = {
            @ApiResponse(description = "Cadastro com sucesso", responseCode = "201"),
            @ApiResponse(description = "1 ou mais Campos inválidos", responseCode = "400",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"400\", \"mensagem\": [ \"email : deve ser um endereço de e-mail bem formado\", \"cpf : CPF deve conter 11 dígitos e pode ou não conter pontos e traços\" ] }"
                            )
                    )
            )
    })
    ResponseEntity<UsuarioDTO> salvar(CriaUsuarioDTO user);

    @Operation(description = "Login de usuários", responses = {
            @ApiResponse(description = "Login realizado com sucesso", responseCode = "200"),
            @ApiResponse(description = "Login e/ou senha inválidos", responseCode = "401",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{\"codigo\": \"401\", \"mensagem\": \"Login e/ou senha inválidos\"}"
                            )
                    )
            ),
            @ApiResponse(description = "E-mail formatado incorretamente", responseCode = "400",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"401\", \"mensagem\": \"email : deve ser um endereço de e-mail bem formado\" }"
                            )
                    )

            )
    })
    ResponseEntity<RespostaDTO> login(LoginDTO login );

    @Operation(description = "Edição de usuários", responses = {
            @ApiResponse(description = "Usuário editado com sucesso", responseCode = "200"),
            @ApiResponse(description = "Usuário não encontrado por id", responseCode = "404",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"404\", \"mensagem\": \"Usuário não encontrado - ID: 1\" } "
                            )
                    )
            ),
            @ApiResponse(description = "1 ou mais campos inválidos", responseCode = "400",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"respostas\": [ \"email : deve ser um endereço de e-mail bem formado\", \"cpf : " +
                                            "CPF deve conter 11 dígitos e pode ou não conter pontos e traços\" ], \"codigo\": 400 }"
                            )
                    )
            )
    })
    ResponseEntity<RespostaDTO> editarDados(Long id, EditaDadosDTO user);

    @Operation(description = "Troca de senha", responses = {
            @ApiResponse(description = "Senha alterada com sucesso", responseCode = "200"),
            @ApiResponse(description = "Usuário não encontrado por id", responseCode = "404",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"404\"\"mensagem\": \"Usuário não encontrado - ID: 1\" }"
                            )
                    )
            ),
            @ApiResponse(description = "Senha antiga incorreta", responseCode = "403",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{\"codigo\": \"403\", \"mensagem\": \"Senha antiga incorreta\"}"
                            )
                    )
            ),
            @ApiResponse(description = "Campos \"Nova senha\" e \"Repetir nova senha\" diferentes",
                    responseCode = "400",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"400\", \"mensagem\": \"Campos nova senha e repetir nova senha devem ser iguais\" }"
                            )
                    )
            ),
    })
    ResponseEntity<RespostaDTO> trocarSenha(Long id, TrocaSenhaDTO user);

    @Operation(description = "Exclusão de usuários", responses = {
            @ApiResponse(description = "Usuário excluído com sucesso", responseCode = "200"),
            @ApiResponse(description = "Usuário não encontrado", responseCode = "404",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"404\" , \"mensagem\": \"Usuário não encontrado - ID: 1\" }"
                            )
                    )
            )
    })
    ResponseEntity<Void> excluir(Long id);

    @Operation(description = "Busca de usuário por id", responses = {
            @ApiResponse(description = "No Content", responseCode = "204"),
            @ApiResponse(description = "Usuário não encontrado", responseCode = "404",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespostaDTO.class),
                            examples = @ExampleObject(
                                    value = "{ \"codigo\": \"404\" , \"mensagem\": \"Usuário não encontrado - ID: 1\" }"
                            )
                    )
            )
    })
    ResponseEntity<UsuarioDTO>  buscarPorId(Long id);
}
