package com.egencodechallenge.orderservice.controllers;

import com.egencodechallenge.orderservice.constants.MessageConstants;
import com.egencodechallenge.orderservice.dtos.ResponseDto;
import com.egencodechallenge.orderservice.dtos.ErrorResponseDto;
import com.egencodechallenge.orderservice.dtos.OrderDto;
import com.egencodechallenge.orderservice.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService service;

    @GetMapping
    ResponseEntity<Object> getOrderById(@RequestParam UUID orderId) {
        logger.info("Processing get Order Request for id : {}", orderId);

        try {
            Optional<OrderDto> order = service.getOrderById(orderId);
            return order.<ResponseEntity<Object>>map(orders -> new ResponseEntity<>(orders, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ErrorResponseDto(MessageConstants.INVALID_ORDER, MessageConstants.NO_SUCH_ORDER + orderId), HttpStatus.OK));
        } catch (Exception ex) {
            logger.error("Exception getting order {}", ex);
            return new ResponseEntity<>(new ErrorResponseDto(MessageConstants.SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {

        logger.info("Processing create order request for customer : {} ", orderDto.getCustomer().getId());

        try {
            return new ResponseEntity<>(service.createOrder(orderDto), HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error("Exception creating order {}", ex);
            return new ResponseEntity<>(new ErrorResponseDto(MessageConstants.SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/cancel")
    ResponseEntity<Object> cancelOrder(@RequestParam UUID orderId) {
        logger.info("Processing cancel Order Request for id : {}", orderId);

        try {
            ResponseDto response = service.cancelOrder(orderId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Exception getting order {}", ex);
            return new ResponseEntity<>(new ErrorResponseDto(MessageConstants.SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
