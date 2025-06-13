package com.example.dto;

public record WeatherResponseMainDto(
        Double temp,
        Integer pressure,
        Integer humidity
) {}
