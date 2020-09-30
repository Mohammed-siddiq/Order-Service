package com.egencodechallenge.orderservice.services;

import com.egencodechallenge.orderservice.dtos.BulkOrderDto;
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
import java.util.stream.Collectors;

/**
 * Defines the bulk order service processing using Kafka cosumer
 */
@Service
public class BulkOrderService {
    @Autowired
    OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(BulkOrderService.class);

    @KafkaListener(id = "orders-listener", topics = "${app.kafka.topic}")
    public void receive(@Payload BulkOrderDto bulkOrder) {

        logger.info("Received bulk order processing by client : {}", bulkOrder.getClientId());

        List<CreateResponseDto> response = bulkOrder.getOrders().parallelStream().map(order -> orderService.createOrder(order)).collect(Collectors.toList());

        logger.info("Bulk Orders processed..");

        logger.info("Sending the bulk order response : {} ", response);
    }

}
