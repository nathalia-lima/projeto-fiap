package com.fiap.java.restaurante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.exceptions.BadRequestException;
import com.fiap.java.restaurante.DTO.RespostaDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaDTO> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(404).body(new RespostaDTO("404", ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RespostaDTO> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.status(400).body(new RespostaDTO ("400", ex.getMessage()));
    }

}
