package com.egencodechallenge.orderservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders extends EntityWithUUID {

    @ManyToOne
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Collection<Item> items;

    @ManyToOne
    private OrderStatus status;



    @ManyToOne
    private Address shippingAddress;

    @ManyToOne
    private Address billingAddress;


    @ManyToOne
    private ShippingMethod shippingMethod;

    private double shippingCharges;
    private double subTotal;

    private double totalTax;

    private double orderTotal;


    @ManyToOne
    private PaymentInfo paymentInfo;

    private Date createdAt = new Date();

    private Date modifiedAt = new Date();


}
