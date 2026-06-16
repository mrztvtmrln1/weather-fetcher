package com.example.dto.order;

import com.example.enums.order.OrderStatuses;

public record StatusChangeOrderDto(
        OrderStatuses statusFrom,
        OrderStatuses statusTo
) {
}
