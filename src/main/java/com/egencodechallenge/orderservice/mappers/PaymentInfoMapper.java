package com.egencodechallenge.orderservice.mappers;

import com.egencodechallenge.orderservice.dtos.PaymentInfoDto;
import com.egencodechallenge.orderservice.models.PaymentInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentInfoMapper {
    PaymentInfoMapper INSTANCE = Mappers.getMapper(PaymentInfoMapper.class);

    PaymentInfoDto getPaymentInfoDto(PaymentInfo paymentInfo);

    PaymentInfo getPaymentInfo(PaymentInfoDto paymentInfo);
}
