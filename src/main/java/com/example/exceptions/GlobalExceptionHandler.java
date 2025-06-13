package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFound(CityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
