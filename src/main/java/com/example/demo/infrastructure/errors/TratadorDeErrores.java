package com.example.demo.infrastructure.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<?> tratarRunTimeException(RuntimeException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("status", 400);
        error.put("timestamp", new Date());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> manejarAccessDenied(AccessDeniedException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "No tienes permisos para acceder a este recurso.");
        error.put("status", 403);
        error.put("timestamp", new Date());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> manejarAuthentication(AuthenticationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "No estás autenticado o tus credenciales son incorrectas.");
        error.put("status", 401);
        error.put("timestamp", new Date());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
