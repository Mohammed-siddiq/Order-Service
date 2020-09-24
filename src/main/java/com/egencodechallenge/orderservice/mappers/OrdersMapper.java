package com.egencodechallenge.orderservice.mappers;


import com.egencodechallenge.orderservice.dtos.OrderDto;
import com.egencodechallenge.orderservice.models.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrdersMapper {

    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    OrderDto getOrdersDto(Orders orders);
    Orders getOrder(OrderDto order);

    List<OrderDto> getOrdersList(List<Orders> orders);

}
