package com.example.endpoints.listener;

import com.example.dto.order.DeliveryStatusChangeDto;
import com.example.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryListener {
    private final OrderService orderService;
    @RabbitListener(queues = "delivery-status-change")
    public void receiveDeliveryStatusChangeMessage(DeliveryStatusChangeDto deliveryStatusChangeDto){
        orderService.deliveryStatusChange(deliveryStatusChangeDto);
    }
}
