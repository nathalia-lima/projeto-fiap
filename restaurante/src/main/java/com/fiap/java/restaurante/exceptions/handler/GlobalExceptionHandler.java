package com.fiap.java.restaurante.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fiap.java.restaurante.exceptions.NotFoundException;
import com.fiap.java.restaurante.DTO.RespostaDTO;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fiap.java.restaurante.exceptions.BadRequestException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaDTO> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(new RespostaDTO("404", ex.getMessage()));
    }

    

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RespostaDTO> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(400).body(new RespostaDTO("400", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespostaDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(400).body(new RespostaDTO("400", "Invalid argument: " + ex.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RespostaDTO> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(400).body(new RespostaDTO("400", "Null pointer exception: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaDTO> handleGenericException(Exception ex) {
        return ResponseEntity.status(500).body(new RespostaDTO("500", "Internal Server Error: " + ex.getMessage()));
    }

    
   @Override
protected ResponseEntity<RespostaDTO> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        org.springframework.http.HttpHeaders headers,
        HttpStatus status,
        org.springframework.web.context.request.WebRequest request) {
    return ResponseEntity.status(400).body(new RespostaDTO("400", ex.getMessage()));
}

}
