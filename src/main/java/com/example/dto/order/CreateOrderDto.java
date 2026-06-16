package com.example.dto.order;

import com.example.enums.order.OrderStatuses;

import java.math.BigDecimal;

public record CreateOrderDto(
        Long orderId,
        OrderStatuses orderStatus,
        BigDecimal totalAmount,
        String deliveryAddress
) {
}
