package com.example.endpoints.controller.order;

import com.example.dto.CommonResponseDto;
import com.example.dto.order.CreateOrderDto;
import com.example.model.order.Order;
import com.example.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public CommonResponseDto<CreateOrderDto> createOrder(@RequestBody Order order) {
        return new CommonResponseDto<>(true, orderService.createOrder(order));
    }
}
