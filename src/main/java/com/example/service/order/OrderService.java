package com.example.service.order;

import com.example.dto.order.CreateOrderDto;
import com.example.dto.order.DeliveryStatusChangeDto;
import com.example.dto.order.StatusChangeOrderDto;
import com.example.enums.order.OrderStatuses;
import com.example.exceptions.OrderStatusMismatchException;
import com.example.mapper.order.OrderMapper;
import com.example.model.order.Order;
import com.example.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    public CreateOrderDto createOrder(Order order){
         return orderMapper.toDto(orderRepository.save(order));
    }
    @Transactional
    public StatusChangeOrderDto changeStatus(StatusChangeOrderDto statusChangeOrderDto, Long orderId){
        Order order = orderRepository.findById(orderId)
                .isPresent() ? orderRepository.findById(orderId).get() : null;
        OrderStatuses currentFromStatus = statusChangeOrderDto.statusFrom();
        if(order == null){
            return null;
        }
        if (order.getOrderStatus() != statusChangeOrderDto.statusFrom()) {
            throw new OrderStatusMismatchException(
                    "Current status is " + order.getOrderStatus() + " but you give " + statusChangeOrderDto.statusFrom()
            );
        }
        order.setOrderStatus(statusChangeOrderDto.statusTo());
        return new StatusChangeOrderDto(currentFromStatus, order.getOrderStatus());
    }

    @Transactional
    public void deliveryStatusChange(DeliveryStatusChangeDto deliveryStatusChangeDto){
        Order order = orderRepository.findById(deliveryStatusChangeDto.orderId())
                .isPresent() ? orderRepository.findById(deliveryStatusChangeDto.orderId()).get() : null;
        if(order == null){
            return;
        }
        if(order.getDeliveryStatus() != deliveryStatusChangeDto.statusFrom()) {
            throw new OrderStatusMismatchException(
                    "Current status is " + order.getDeliveryStatus() + " but you give " + deliveryStatusChangeDto.statusFrom()
            );
        }
        order.setDeliveryStatus(deliveryStatusChangeDto.statusTo());
    }
}
