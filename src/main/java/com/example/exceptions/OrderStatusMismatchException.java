package com.example.exceptions;

public class OrderStatusMismatchException extends RuntimeException {
    public OrderStatusMismatchException(String message) {
        super(message);
    }
}
