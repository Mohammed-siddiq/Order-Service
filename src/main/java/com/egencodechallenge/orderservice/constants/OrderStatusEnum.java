package com.egencodechallenge.orderservice.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    CREATED,
    PAYMENT_PROCESSING,
    COMPLETE,
    CANCELLED

}
