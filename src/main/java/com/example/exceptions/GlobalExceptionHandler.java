package com.example.exceptions;

import com.example.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFound(CityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(OrderStatusMismatchException.class) // Твое кастомное исключение
    public ResponseEntity<CommonResponseDto<String>> handleOrderStatusMismatch(OrderStatusMismatchException ex) {
        CommonResponseDto<String> response = new CommonResponseDto<>(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
}
