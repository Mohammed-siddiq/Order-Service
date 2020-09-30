package com.egencodechallenge.orderservice.mappers;

import com.egencodechallenge.orderservice.dtos.ShippingMethodDto;
import com.egencodechallenge.orderservice.models.ShippingMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShippingMethodMapper {
    ShippingMethodMapper INSTANCE = Mappers.getMapper(ShippingMethodMapper.class);

    ShippingMethod getShippingMethod(ShippingMethodDto shippingMethodDto);
}
