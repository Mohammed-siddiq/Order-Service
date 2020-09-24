package com.egencodechallenge.orderservice.dtos;

import com.egencodechallenge.orderservice.models.Address;
import com.egencodechallenge.orderservice.models.Item;
import com.egencodechallenge.orderservice.models.OrderStatus;
import com.egencodechallenge.orderservice.models.ShippingMethod;
import lombok.Data;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {



    private UUID orderId;

    private CustomerDto customer;

    private List<ItemDto> items;

    private ShippingMethodDto shippingMethod;
    private AddressDto shippingAddress;

    private AddressDto billingAddress;

    private OrderStatusDto orderStatus;

    private PaymentInfoDto paymentInfo;

    private double shippingCharges;
    private double subTotal;

    private double totalTax;

    private double orderTotal;

    private DateTime createdAt;

    private DateTime modifiedAt;


}
