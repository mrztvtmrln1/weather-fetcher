package com.example.service.service;

import com.example.config.OpenWeatherConfig;
import com.example.dto.WeatherResponseDto;
import com.example.endpoints.feign.WeatherClient;
import com.example.endpoints.publisher.KafkaProducer;
import com.example.mapper.WeatherMapper;
import com.example.model.City;
import com.example.model.Weather;
import com.example.repository.CityRepository;
import com.example.repository.WeatherRepository;
import com.example.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock private WeatherClient weatherClient;
    @Mock private WeatherMapper weatherMapper;
    @Mock private CityRepository cityRepository;
    @Mock private WeatherRepository weatherRepository;
    @Mock private OpenWeatherConfig openWeatherConfig;
    @Mock private KafkaProducer kafkaProducer;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService(
                weatherClient,
                weatherMapper,
                cityRepository,
                weatherRepository,
                openWeatherConfig,
                kafkaProducer
        );
    }


    @InjectMocks
    private WeatherService weatherService;

    @Test
    void getLastWeather_returnsLastWeatherFromRepository() {
        City city = mock(City.class);
        when(city.getId()).thenReturn(42L);

        Weather expectedWeather = mock(Weather.class);

        when(cityRepository.findByName("Almaty")).thenReturn(Optional.of(city));
        when(weatherRepository.findTopByCity_IdOrderByTimestampDesc(42L))
                .thenReturn(Optional.of(expectedWeather));

        Optional<Weather> result = weatherService.getLastWeather("Almaty");

        assertTrue(result.isPresent());
        assertSame(expectedWeather, result.get());

        verify(cityRepository).findByName("Almaty");
        verify(weatherRepository).findTopByCity_IdOrderByTimestampDesc(42L);
        verifyNoInteractions(weatherClient, weatherMapper, openWeatherConfig, kafkaProducer);
    }
    @Test
    void getWeather_returnsCurrentWeatherFromRepository() {
        String cityName = "Almaty";
        String key = "Key";

        WeatherResponseDto  weatherResponseDto = mock(WeatherResponseDto.class);

        when(weatherResponseDto.name()).thenReturn(cityName);

        when(openWeatherConfig.getKey()).thenReturn(key);

        when(weatherClient.getWeatherByCity(cityName, key, "metric")).thenReturn(weatherResponseDto);

        City city = new City();
        city.setName("Almaty");

        when(cityRepository.findByName("Almaty")).thenReturn(Optional.of(city));
        Weather mapped = new Weather();

        when(weatherMapper.toEntity(weatherResponseDto)).thenReturn(mapped);
        when(weatherRepository.save(any(Weather.class)))
                .thenAnswer(inv -> inv.getArgument(0, Weather.class));


        WeatherService spyService = spy(weatherService);
        doReturn(true).when(spyService).needToSendToQueue(eq(cityName), same(weatherResponseDto));

        Weather result = spyService.getWeather(cityName);

        verify(weatherClient).getWeatherByCity(cityName, key, "metric");
        verify(cityRepository).findByName("Almaty");
        verify(weatherMapper).toEntity(weatherResponseDto);

        ArgumentCaptor<Weather> captor = ArgumentCaptor.forClass(Weather.class);
        verify(weatherRepository).save(captor.capture());
        Weather toSave = captor.getValue();

        assertSame(city, toSave.getCity(), "Должен проставиться city перед save()");
        assertSame(toSave, result, "Метод должен вернуть сохранённую сущность (то что вернул репозиторий)");

        verify(kafkaProducer).send(weatherResponseDto);
        verifyNoMoreInteractions(kafkaProducer);

    }
}
