package com.example.dto;

public record ChangeStatusRequest(
        Long userId,
        Boolean newStatus
) {
}
