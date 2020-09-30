package com.egencodechallenge.orderservice.mappers;

import com.egencodechallenge.orderservice.dtos.PaymentMethodDto;
import com.egencodechallenge.orderservice.models.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMethodMapper {
    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    PaymentMethodDto getPaymentMethodDto(PaymentMethod paymentMethod);
}
