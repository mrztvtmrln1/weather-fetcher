package com.example.service;
import com.example.enums.ClothBodyType;
import com.example.model.Cloth;
import com.example.model.Weather;
import com.example.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final WeatherService weatherService;
    private final CompatibleColorService compatibleColorService;

    public List<Cloth> clothesForDay(String city, Long baseClothId){
        Weather weather = getActualWeather(city);
        boolean isWearableInWind = weather.getWindSpeed() < 5.0;
        List<Cloth> suitableClothes =  clothRepository
               .findByTempRangeAndWind((int)Math.round(weather.getTemperature()),isWearableInWind);
        Optional<Cloth> baseCloth = getClothById(baseClothId);
        List<String> allCompatibleColors = compatibleColorService.allCompatibleColors(baseCloth.get().getClothColor());

        List<Cloth> outfitForDay = new ArrayList<>();

        Set<ClothBodyType> seenBodyTypes = new HashSet<>();
        seenBodyTypes.add(baseCloth.get().getBodyType());
        outfitForDay.add(baseCloth.get());

        for(Cloth cloth : suitableClothes){
            if(allCompatibleColors.contains(cloth.getClothColor().toString())){
                if(!seenBodyTypes.contains(cloth.getBodyType())){
                    outfitForDay.add(cloth);
                    seenBodyTypes.add(cloth.getBodyType());
                }
            }
        }
        return outfitForDay;
    }

    public List<Cloth> allClothesForCity(String city){
        Weather weather = getActualWeather(city);
        boolean isWearableInWind = weather.getWindSpeed() < 5.0;
        return clothRepository.findByTempRangeAndWind((int)Math
                .round(weather.getTemperature()),isWearableInWind);
    }

    public Cloth save(Cloth cloth){
        return clothRepository.save(cloth);
    }

    public Weather getActualWeather(String city){
        return weatherService.getLastWeather(city)
                .orElseThrow(() -> new RuntimeException("Weather not found"));
    }
    
    public Optional<Cloth> getClothById(Long clothId){
        return clothRepository.findById(clothId);
    }
}
