package com.example.dto;

import com.example.enums.UserStatus;

public record UserResponseDto(
        Long userId,
        String login,
        UserStatus status
) {
}
