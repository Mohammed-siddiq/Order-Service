package com.egencodechallenge.orderservice.services;

import com.egencodechallenge.orderservice.dtos.CreateResponseDto;
import com.egencodechallenge.orderservice.dtos.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Defines the bulk order service processing using Kafka cosumer
 */
@Service
public class BulkOrderService {
    @Autowired
    OrderService orderService;
    private static final Logger LOG = LoggerFactory.getLogger(BulkOrderService.class);

    @KafkaListener(id = "orders-listener", topics = "${app.kafka.topic}")
    public void receive(@Payload OrderDto order) {

        LOG.info("creating order='{}'", order);

        CompletableFuture<CreateResponseDto> response = orderService.createOrderAsync(order);

        try {
            LOG.info("Created order with order Id: {}", response.get().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
