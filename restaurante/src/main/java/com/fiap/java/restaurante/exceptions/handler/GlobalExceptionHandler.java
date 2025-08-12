package com.fiap.java.restaurante.exceptions.handler;

import com.fiap.java.restaurante.exceptions.BadRequestException;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.exceptions.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaDTO> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(new RespostaDTO("404", ex.getMessage()));
    }

    

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RespostaDTO> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.status(400).body(new RespostaDTO ("400", ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespostaDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.status(400).body(new RespostaDTO("400", "Request body não está bem formado ou está vazio"));
    }       

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage())).toList();

        return ResponseEntity.status(400).body(new RespostaDTO("400", erros.toString()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RespostaDTO> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String errorMessage = String.format("O parâmetro '%s' deve ser do tipo '%s'", 
                ex.getName(), ex.getRequiredType().getSimpleName());
        return ResponseEntity.status(400).body(new RespostaDTO("400", errorMessage));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespostaDTO> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        return ResponseEntity.status(405).body(new RespostaDTO("405", "Método HTTP não suportado: " + ex.getMethod()));
    }    
   

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RespostaDTO> handleNullPointerException(NullPointerException ex, WebRequest request) {
        return ResponseEntity.status(400).body(new RespostaDTO("400", "Null Pointer: " + ex.getMessage()));
    }   

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RespostaDTO> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        return ResponseEntity.status(401).body(new RespostaDTO("401", ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaDTO> handleGenericException(Exception ex, WebRequest request) {
        return ResponseEntity.status(500).body(new RespostaDTO("500", "Ocorreu um erro interno no servidor: " + ex.getMessage()));
    }

}
