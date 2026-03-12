package com.example.dto;

import com.example.enums.UserStatus;

public record ChangeStatusRequest(
        Long userId,
        UserStatus newStatus
) {
}
