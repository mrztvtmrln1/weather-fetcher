package com.example.service;
import com.example.enums.ClothBodyType;
import com.example.enums.WearType;
import com.example.model.Cloth;
import com.example.model.Weather;
import com.example.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final WeatherService weatherService;
    private final CompatibleColorService compatibleColorService;

    private static final int MAX_LAYERS_PER_BODY = 3;

    public List<Cloth> clothesForDay(String city, Long baseClothId) {
        Weather weather = getActualWeather(city);
        boolean isWearableInWind = weather.getWindSpeed() < 5.0;

        List<Cloth> suitableClothes = clothRepository
                .findByTempRangeAndWind(
                        (int) Math.round(weather.getTemperature()),
                        isWearableInWind,
                        Pageable.unpaged()
                );

        Cloth baseCloth = getClothById(baseClothId)
                .orElseThrow(() -> new IllegalArgumentException("Base cloth not found: " + baseClothId));

        if (baseCloth.getBodyType() == null || baseCloth.getWearType() == null) {
            throw new IllegalStateException("Base cloth must have bodyType and wearType");
        }

        List<String> compatibleColors =
                compatibleColorService.allCompatibleColors(baseCloth.getClothColor());

        List<Cloth> result = new ArrayList<>();
        result.add(baseCloth);

        Map<ClothBodyType, EnumSet<WearType>> occupied = new HashMap<>();
        occupied.put(baseCloth.getBodyType(), EnumSet.of(baseCloth.getWearType()));

        for (Cloth cloth : suitableClothes) {
            if (cloth.getId().equals(baseClothId)) continue;

            if (cloth.getBodyType() == null || cloth.getWearType() == null) {
                continue;
            }

            if (cloth.getClothColor() == null) {
                continue;
            }

            if (!compatibleColors.contains(cloth.getClothColor().toString())) {
                continue;
            }

            ClothBodyType bodyType = cloth.getBodyType();
            WearType wearType = cloth.getWearType();

            EnumSet<WearType> used = occupied.get(bodyType);
            if (used == null) {
                used = EnumSet.noneOf(WearType.class);
                occupied.put(bodyType, used);
            }

            if (used.contains(wearType)) continue;
            if (used.size() >= MAX_LAYERS_PER_BODY) continue;

            result.add(cloth);
            used.add(wearType);
        }

        return result;
    }

    public List<Cloth> allClothesForCity(String city, Pageable pageable) {
        Weather weather = getActualWeather(city);
        boolean isWearableInWind = weather.getWindSpeed() < 5.0;
        return clothRepository.findByTempRangeAndWind((int)Math
                .round(weather.getTemperature()),isWearableInWind, pageable);
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
