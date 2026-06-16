package com.example.service.order;

import com.example.dto.order.CreateOrderDto;
import com.example.mapper.order.OrderMapper;
import com.example.model.order.Order;
import com.example.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    public CreateOrderDto createOrder(Order order){
         return orderMapper.toDto(orderRepository.save(order));
    }
}
