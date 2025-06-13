package com.example.mapper;

import com.example.dto.WeatherResponseDto;
import com.example.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mapping(target = "temperature", source = ".", qualifiedByName = "extractTemperature")
    @Mapping(target = "pressure", source = ".", qualifiedByName = "extractPressure")
    @Mapping(target = "humidity", source = ".", qualifiedByName = "extractHumidity")
    @Mapping(target = "windSpeed", source = ".", qualifiedByName = "extractWindSpeed")
    @Mapping(target = "description", source = ".", qualifiedByName = "extractDescription")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "city", ignore = true)
    Weather toEntity(WeatherResponseDto dto);

    @Named("extractTemperature")
    static Double extractTemperature(WeatherResponseDto dto) {
        return dto.main() != null ? dto.main().temp() : null;
    }

    @Named("extractPressure")
    static Integer extractPressure(WeatherResponseDto dto) {
        return dto.main() != null ? dto.main().pressure() : null;
    }

    @Named("extractHumidity")
    static Integer extractHumidity(WeatherResponseDto dto) {
        return dto.main() != null ? dto.main().humidity() : null;
    }

    @Named("extractWindSpeed")
    static Double extractWindSpeed(WeatherResponseDto dto) {
        return dto.wind() != null ? dto.wind().speed() : null;
    }

    @Named("extractDescription")
    static String extractDescription(WeatherResponseDto dto) {
        if (dto.weather() != null && !dto.weather().isEmpty()) {
            return dto.weather().get(0).description();
        }
        return null;
    }
}
