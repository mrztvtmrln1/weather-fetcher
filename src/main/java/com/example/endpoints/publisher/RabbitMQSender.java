package com.example.endpoints.publisher;

import com.example.config.RabbitMQConfig;
import com.example.dto.WeatherResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMQSender(RabbitTemplate rabbitTemplate,ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(WeatherResponseDto weatherResponseDto) {
        try{
            String json = objectMapper.writeValueAsString(weatherResponseDto);
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    json
            );
        }catch (Exception e){
            throw new RuntimeException("Failed to send message", e);
        }
    }
}
