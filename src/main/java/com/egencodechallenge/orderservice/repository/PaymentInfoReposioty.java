package com.egencodechallenge.orderservice.repository;

import com.egencodechallenge.orderservice.models.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInfoReposioty extends JpaRepository<PaymentInfo, Long> {
}
