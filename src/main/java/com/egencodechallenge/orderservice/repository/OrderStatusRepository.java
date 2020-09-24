package com.egencodechallenge.orderservice.repository;

import com.egencodechallenge.orderservice.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {

    OrderStatus findByStatus(String status);
}
