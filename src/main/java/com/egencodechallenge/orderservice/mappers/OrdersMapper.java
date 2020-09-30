package com.egencodechallenge.orderservice.mappers;


import com.egencodechallenge.orderservice.dtos.OrderDto;
import com.egencodechallenge.orderservice.models.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper
public abstract class OrdersMapper {

    public static OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    public abstract OrderDto getOrdersDto(Orders orders);

    public abstract Orders getOrder(OrderDto order);

    public OrderDto generateOrderDto(Orders order) {
        OrderDto orderDto = getOrdersDto(order);
        orderDto.setOrderId(order.getId());
        return orderDto;
    }

}
