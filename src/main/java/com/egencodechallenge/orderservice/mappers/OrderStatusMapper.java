package com.egencodechallenge.orderservice.mappers;

import com.egencodechallenge.orderservice.dtos.OrderStatusDto;
import com.egencodechallenge.orderservice.models.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderStatusMapper {

    OrderStatusMapper INSTANCE = Mappers.getMapper(OrderStatusMapper.class);

    OrderStatusDto getOrders(OrderStatus status);




}
