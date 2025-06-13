package com.example.endpoints.publisher;

import com.example.config.RabbitMQConfig;
import com.example.dto.WeatherResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(WeatherResponseDto weatherResponseDto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                weatherResponseDto
        );
        System.out.println("Сообщение отправлено: " + weatherResponseDto);
    }
}
