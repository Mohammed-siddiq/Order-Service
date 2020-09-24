package com.egencodechallenge.orderservice.repository;

import com.egencodechallenge.orderservice.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {


}
