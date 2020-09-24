package com.egencodechallenge.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentInfo{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private PaymentMethod paymentMethod;

    private DateTime paymentDate;

    private long confirmationNumber;

    @ManyToMany
    private Collection<Card> cards;

    private DateTime createdAt = new DateTime(DateTimeZone.UTC);


}
