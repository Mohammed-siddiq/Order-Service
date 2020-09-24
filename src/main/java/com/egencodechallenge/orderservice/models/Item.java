package com.egencodechallenge.orderservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull
    private String name;

    private double unitCost;

    private DateTime createdAt = new DateTime(DateTimeZone.UTC);

    private boolean active;

    @ManyToMany(mappedBy = "items")
    private Collection<Orders> orders;



//    private Collections<>






}
