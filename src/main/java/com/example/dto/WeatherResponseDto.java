package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO с данными о погоде")
public record WeatherResponseDto(
        @Schema(description = "Название города")
        String name,

        @Schema(description = "Основные параметры погоды")
        WeatherResponseMainDto main,

        @Schema(description = "Информация о ветре")
        WeatherResponseWindDto wind,

        @Schema(description = "Информация о погоде")
        List<WeatherResponseDescriptionDto> weather
) {}

