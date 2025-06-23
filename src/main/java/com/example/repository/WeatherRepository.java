package com.example.repository;

import com.example.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WeatherRepository extends JpaRepository<Weather,Long> {
    Optional<Weather> findTopByCity_IdOrderByTimestampDesc(Long cityId);
}
