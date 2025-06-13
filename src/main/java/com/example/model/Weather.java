package com.example.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(nullable = false)
    private Double temperature;

    private Integer pressure;

    private Integer humidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    private String description;

    private LocalDateTime timestamp;

    public Weather(Long id, City city, Double temperature, Integer pressure, Integer humidity, Double windSpeed, String description, LocalDateTime timestamp) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
        this.timestamp = timestamp;
    }
}
