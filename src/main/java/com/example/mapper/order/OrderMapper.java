package com.example.mapper.order;

import com.example.dto.order.CreateOrderDto;
import com.example.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "id", target = "orderId")
    CreateOrderDto toDto(Order order);
}
