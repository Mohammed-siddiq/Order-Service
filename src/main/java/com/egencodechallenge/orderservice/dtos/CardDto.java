package com.egencodechallenge.orderservice.dtos;

import lombok.Data;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;@Data
public class CardDto {

    long id;
    private String cardNumber;

    private String name;

    private DateTime expiryDate;

    private DateTime createdAt;

}
