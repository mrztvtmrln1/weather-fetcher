package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String DELIVERY_STATUS_CHANGE_TOPIC =
            "delivery-status-change";

    public static final String WEATHER_DATA_CHANGE_TOPIC =
            "weather-data-change";

    @Bean
    public NewTopic deliveryStatusChangeTopic() {
        return TopicBuilder
                .name(DELIVERY_STATUS_CHANGE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic weatherTopic() {
        return TopicBuilder.name(WEATHER_DATA_CHANGE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}