package com.example.service;

import com.example.model.Cloth;
import com.example.model.Weather;
import com.example.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final WeatherService weatherService;

    public Cloth createCloth(Cloth cloth) {
        return clothRepository.save(cloth);
    }

    public List<Cloth> clothesForDay(String city){
       Weather weather = weatherService.getLastWeather(city)
               .orElseThrow(() -> new RuntimeException("Weather not found"));
       boolean isWearableInWind = weather.getWindSpeed() < 5.0;
       return clothRepository.findByTempRangeAndWind((int)Math.round(weather.getTemperature()),isWearableInWind);
    }
}
