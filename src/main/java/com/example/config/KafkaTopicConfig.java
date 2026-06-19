package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String DELIVERY_STATUS_CHANGE_TOPIC =
            "delivery-status-change";

    @Bean
    public NewTopic deliveryStatusChangeTopic() {
        return TopicBuilder
                .name(DELIVERY_STATUS_CHANGE_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}