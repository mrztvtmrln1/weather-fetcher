package com.example.dto.order;

import com.example.enums.order.DeliveryStatuses;

public record DeliveryStatusChangeDto(
        Long orderId,
        DeliveryStatuses statusFrom,
        DeliveryStatuses statusTo
) {
}
