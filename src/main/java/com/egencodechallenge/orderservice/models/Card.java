package com.egencodechallenge.orderservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;@Entity
@Data
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull
    private String cardNumber;

    @NotNull
    private String name;

    @NotNull
    private DateTime expiryDate;


    private DateTime createdAt = new DateTime(DateTimeZone.UTC);


}