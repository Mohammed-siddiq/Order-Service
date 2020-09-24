package com.egencodechallenge.orderservice.mappers;

import com.egencodechallenge.orderservice.dtos.AddressDto;
import com.egencodechallenge.orderservice.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto getAddressDto(Address address);
}
