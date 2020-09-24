package com.egencodechallenge.orderservice.dtos;

import lombok.Data;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;import java.util.List;

@Data
public class PaymentInfoDto {

    private long orderId;

    private PaymentMethodDto paymentMethod;

    private DateTime paymentDate;


    private long confirmationNumber;

    private List<CardDto> cards;


}
