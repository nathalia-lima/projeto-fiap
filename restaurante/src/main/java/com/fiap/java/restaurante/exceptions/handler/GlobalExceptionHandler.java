package com.fiap.java.restaurante.exceptions.handler;

import com.fiap.java.restaurante.DTO.ValidacaoRespostasDto;
import com.fiap.java.restaurante.exceptions.BadRequestException;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.exceptions.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaDTO> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(404).body(new RespostaDTO("404", ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RespostaDTO> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.status(400).body(new RespostaDTO ("400", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoRespostasDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage())).toList();

        return ResponseEntity.status(400).body(new ValidacaoRespostasDto(erros, 400));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RespostaDTO> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        return ResponseEntity.status(401).body(new RespostaDTO("401", ex.getReason()));
    }
}
