package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;
@Schema(description = "DTO с данными о погоде")
public record WeatherResponseDto(
        Map<String, Object> main,
        Map<String, Object> wind,
        List<Map<String, String>> weather,
        String name)
{}
