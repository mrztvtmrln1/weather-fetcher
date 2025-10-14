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
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final WeatherService weatherService;
    private final CompatibleColorService compatibleColorService;

    public List<Cloth> clothesForDay(String city){
       Weather weather = getActualWeather(city);
       boolean isWearableInWind = weather.getWindSpeed() < 5.0;

       List<Cloth> suitableClothes =  clothRepository
               .findByTempRangeAndWind((int)Math.round(weather.getTemperature()),isWearableInWind);
       Map<ClothBodyType,Cloth> map = new HashMap<>();
       List<Cloth> suitableClothByColor = new ArrayList<>();
       suitableClothByColor.add(suitableClothes.getFirst());

        IntStream.range(1, suitableClothes.size())
                .filter(i -> compatibleColorService.areColorsCompatible(
                        suitableClothes.get(i).getClothColor(),
                        suitableClothes.get(i - 1).getClothColor()))
                .mapToObj(suitableClothes::get)
                .forEach(suitableClothByColor::add);

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
}
