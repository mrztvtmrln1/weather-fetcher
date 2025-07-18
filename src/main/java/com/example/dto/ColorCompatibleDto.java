package com.example.dto;

import com.example.enums.ClothColors;

public record ColorCompatibleDto(
        ClothColors colorOne,
        ClothColors colorTwo
) {
}
