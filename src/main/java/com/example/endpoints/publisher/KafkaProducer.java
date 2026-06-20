package com.example.endpoints.publisher;

import com.example.config.KafkaTopicConfig;
import com.example.dto.WeatherResponseDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, WeatherResponseDto> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, WeatherResponseDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(WeatherResponseDto weatherResponseDto) {
        kafkaTemplate.send(
                KafkaTopicConfig.WEATHER_DATA_CHANGE_TOPIC,
                weatherResponseDto
        );
        System.out.println("Сообщение отправлено в Kafka: " + weatherResponseDto);
    }

}
