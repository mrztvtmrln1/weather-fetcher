package com.example.dto;

public record UserResponseDto(
        Long userId,
        String login,
        Boolean status
) {
}
