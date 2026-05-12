package com.example.dto;

import com.example.enums.UserStatus;

public record UserInfoByIdDto(
        Long id,
        String firstName,
        String lastName,
        String login,
        Integer profileId,
        UserStatus status
) {}
