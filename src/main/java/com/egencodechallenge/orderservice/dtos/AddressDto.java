package com.egencodechallenge.orderservice.dtos;

import lombok.Data;

@Data
public class AddressDto {

    private long id;

    private String addressLine1;

    private String addressLine2;

    private CityDto city;

    private StateDto state;

    private String zipCode;

}
