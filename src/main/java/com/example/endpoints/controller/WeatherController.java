package com.example.endpoints.controller;

import com.example.model.Weather;
import com.example.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@Tag(name = "Weather", description = "Контроллер для работы с погодой")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    @Operation(summary = "Получить погоду по городу", description = "Возвращает актуальную погоду по указанному городу")
    public Weather getWeatherByCity(@RequestParam String city) {
        return weatherService.getWeather(city);
    }
}
