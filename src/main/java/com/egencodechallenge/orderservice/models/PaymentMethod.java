package com.egencodechallenge.orderservice.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;@Entity
@Data
public class PaymentMethod{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String method;

    private boolean active;

    private DateTime createdAt = new DateTime(DateTimeZone.UTC);

}
