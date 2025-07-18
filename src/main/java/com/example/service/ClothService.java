package com.example.service;
import com.example.enums.ClothBodyType;
import com.example.model.Cloth;
import com.example.model.Weather;
import com.example.repository.ClothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final WeatherService weatherService;
    private final CompatibleColorService compatibleColorService;

    public List<Cloth> clothesForDay(String city){
       Weather weather = weatherService.getLastWeather(city)
               .orElseThrow(() -> new RuntimeException("Weather not found"));
       boolean isWearableInWind = weather.getWindSpeed() < 5.0;
       List<Cloth> suitableClothes =  clothRepository
               .findByTempRangeAndWind((int)Math.round(weather.getTemperature()),isWearableInWind);
       Map<ClothBodyType,Cloth> map = new HashMap<>();
       List<Cloth> suitableClothByColor = new ArrayList<>();
       suitableClothByColor.add(suitableClothes.getFirst());
       for(int i=1;i<suitableClothes.size();i++){
           if(compatibleColorService.areColorsCompatible(suitableClothes
                   .get(i).getClothColor(),suitableClothes.get(i-1).getClothColor())){
               suitableClothByColor.add(suitableClothes.get(i));
           }
       }
       List<Cloth> lookForDay = new ArrayList<>();
       for(Cloth cloth : suitableClothByColor){
           if(!map.containsKey(cloth.getBodyType())){
               map.put(cloth.getBodyType(),cloth);
               lookForDay.add(cloth);
           }
       }
       return lookForDay;
    }

    public List<Cloth> allClothesForCity(String city){
        Weather weather = weatherService.getLastWeather(city)
                .orElseThrow(() -> new RuntimeException("Weather not found"));
        boolean isWearableInWind = weather.getWindSpeed() < 5.0;
        return clothRepository.findByTempRangeAndWind((int)Math
                .round(weather.getTemperature()),isWearableInWind);
    }
}
