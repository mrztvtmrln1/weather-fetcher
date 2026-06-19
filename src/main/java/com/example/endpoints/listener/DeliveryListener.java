package com.example.endpoints.listener;

import com.example.config.KafkaTopicConfig;
import com.example.dto.order.DeliveryStatusChangeDto;
import com.example.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryListener {
    private final OrderService orderService;
    @KafkaListener(
            topics = KafkaTopicConfig.DELIVERY_STATUS_CHANGE_TOPIC,
            groupId = "delivery-service"
    )
    public void receiveDeliveryStatusChangeMessage(DeliveryStatusChangeDto deliveryStatusChangeDto){
        orderService.deliveryStatusChange(deliveryStatusChangeDto);
    }
}
