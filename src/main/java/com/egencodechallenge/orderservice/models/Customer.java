package com.egencodechallenge.orderservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Address shippingAddress;

    @ManyToOne
    private Address billingAddress;

    private DateTime createdAt = new DateTime(DateTimeZone.UTC);

    @OneToMany
    private Collection<Card> cards;


}
