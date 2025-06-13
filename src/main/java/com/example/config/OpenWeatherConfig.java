package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "openweather.api")
public class OpenWeatherConfig {
    private String key;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
