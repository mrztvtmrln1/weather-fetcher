package com.example.service;

import com.example.config.OpenWeatherConfig;
import com.example.dto.WeatherResponseDto;
import com.example.endpoints.feign.WeatherClient;
import com.example.endpoints.publisher.RabbitMQSender;
import com.example.exceptions.CityNotFoundException;
import com.example.mapper.WeatherMapper;
import com.example.model.City;
import com.example.model.Weather;
import com.example.repository.CityRepository;
import com.example.repository.WeatherRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;
    private final OpenWeatherConfig openWeatherConfig;
    private final RabbitMQSender rabbitMQSender;

    HashMap<String,WeatherResponseDto> cache = new HashMap<String,WeatherResponseDto>();

    public WeatherService(WeatherClient weatherClient, WeatherMapper weatherMapper, CityRepository cityRepository, WeatherRepository weatherRepository, OpenWeatherConfig openWeatherConfig, RabbitMQSender rabbitMQSender) {
        this.weatherClient = weatherClient;
        this.weatherMapper = weatherMapper;
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
        this.openWeatherConfig = openWeatherConfig;
        this.rabbitMQSender = rabbitMQSender;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void fetchWeatherForAllCities() {
        List<String> cities = List.of("Almaty", "Nur-Sultan", "Shymkent");
        for (String city : cities) {
            try {
                System.out.println("Fetching weather for " + city);
                getWeather(city);
            } catch (Exception e) {
                System.err.println("Failed to fetch weather for " + city + ": " + e.getMessage());
            }
        }
    }

    public boolean needToSendToQueue(String city,WeatherResponseDto weatherResponseDto) {
        Double currentTemp = weatherResponseDto.main().temp();
        Double lastTemp = cache.get(city).main().temp();
        return currentTemp - lastTemp >= 5.0 || currentTemp - lastTemp <= -5.0;
    }


    public Weather getWeather(String cityName) {
        WeatherResponseDto weatherResponseDto = weatherClient
                .getWeatherByCity(cityName,openWeatherConfig.getKey(),"metric");
        City city = cityRepository.findByName(weatherResponseDto.name())
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        Weather weather = weatherMapper.toEntity(weatherResponseDto);
        weather.setCity(city);
        if(needToSendToQueue(cityName,weatherResponseDto)){
            rabbitMQSender.send(weatherResponseDto);
        }
        return weatherRepository.save(weather);
    }
}
