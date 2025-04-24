package com.example.mapper;

import com.example.dto.WeatherResponseDto;
import com.example.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(target = "temperature", source = ".", qualifiedByName = "extractTemp")
    @Mapping(target = "pressure", source = ".", qualifiedByName = "extractPressure")
    @Mapping(target = "humidity", source = ".", qualifiedByName = "extractHumidity")
    @Mapping(target = "windSpeed", source = ".", qualifiedByName = "extractWindSpeed")
    @Mapping(target = "description", source = ".", qualifiedByName = "extractDescription")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "city", ignore = true)
    Weather toEntity(WeatherResponseDto dto);

    @Named("extractTemp")
    static Double extractTemp(WeatherResponseDto dto) {
        Object val = dto.main().get("temp");
        return val != null ? Double.parseDouble(val.toString()) : null;
    }

    @Named("extractPressure")
    static Integer extractPressure(WeatherResponseDto dto) {
        Object val = dto.main().get("pressure");
        return val != null ? Integer.parseInt(val.toString()) : null;
    }

    @Named("extractHumidity")
    static Integer extractHumidity(WeatherResponseDto dto) {
        Object val = dto.main().get("humidity");
        return val != null ? Integer.parseInt(val.toString()) : null;
    }

    @Named("extractWindSpeed")
    static Double extractWindSpeed(WeatherResponseDto dto) {
        Object val = dto.wind().get("speed");
        return val != null ? Double.parseDouble(val.toString()) : null;
    }

    @Named("extractDescription")
    static String extractDescription(WeatherResponseDto dto) {
        if (dto.weather() != null && !dto.weather().isEmpty()) {
            return dto.weather().get(0).get("description");
        }
        return null;
    }
}

