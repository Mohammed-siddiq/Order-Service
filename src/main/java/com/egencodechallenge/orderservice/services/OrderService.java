package com.egencodechallenge.orderservice.services;

import com.egencodechallenge.orderservice.constants.MessageConstants;
import com.egencodechallenge.orderservice.constants.OrderStatusEnum;
import com.egencodechallenge.orderservice.dtos.CreateResponseDto;
import com.egencodechallenge.orderservice.dtos.OrderDto;
import com.egencodechallenge.orderservice.dtos.PaymentInfoDto;
import com.egencodechallenge.orderservice.mappers.OrdersMapper;
import com.egencodechallenge.orderservice.mappers.PaymentInfoMapper;
import com.egencodechallenge.orderservice.models.Orders;
import com.egencodechallenge.orderservice.models.PaymentInfo;
import com.egencodechallenge.orderservice.repository.OrderRepository;
import com.egencodechallenge.orderservice.repository.OrderStatusRepository;
import com.egencodechallenge.orderservice.repository.PaymentInfoReposioty;
import com.egencodechallenge.orderservice.utils.OrderServiceUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Service manage orders
 */
@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentInfoReposioty paymentRepository;

    @Autowired
    private OrderServiceUtil orderServiceUtil;

    @Autowired
    private OrderStatusRepository orderStatus;


    /***
     * Retrieves specific order based on the Id specied
     * @param orderId
     * @return
     */
    public OrderDto getOrderById(String orderId) {
        logger.info("Getting order {}", orderId);


        Optional<Orders> order = orderRepository.findById(UUID.fromString(orderId));

        if (order.isPresent()) {

            logger.info("Successfully received order info for customer {}", order.get().getCustomer().getId());


            return OrdersMapper.INSTANCE.getOrdersDto(order.get());

        }
        //TODO: handle
        return null;

    }

    /**
     * Creates an order
     *
     * @param orderDto The {@link OrderDto} having order info
     * @return {@link CreateResponseDto} having new id
     */
    public CreateResponseDto createOrder(OrderDto orderDto) {
        try {

            //Extracting payment information to be sent to payment service
            PaymentInfo paymentInfo = PaymentInfoMapper.INSTANCE.getPaymentInfo(orderDto.getPaymentInfo());
            PaymentInfo newPayment = paymentRepository.save(paymentInfo);


            Orders newOrder = OrdersMapper.INSTANCE.getOrder(orderDto);
            newOrder.setPaymentInfo(newPayment);

            newOrder.setStatus(orderStatus.findByStatus(OrderStatusEnum.CREATED.name()));


            Orders createdOrder = orderRepository.save(newOrder);

            logger.info("Processing payment for order {} ", createdOrder.getId());

            //Call to payment service with payment Information. This service sets the status of the order to PAYMENT_PROCESSING before processing the payment
            // returns the confirmation number


            createdOrder.setStatus(orderStatus.findByStatus(OrderStatusEnum.COMPLETE.name()));
            orderRepository.save(createdOrder);


            logger.info("Created order : {} ", newOrder.getId());
            logger.info("Creating order for customer : {}", orderDto.getCustomer().getId());
            return new CreateResponseDto(MessageConstants.createMessage, newOrder.getId().toString());
        } catch (Exception ex) {
            logger.error("Exception creating order {}", ex);
            return new CreateResponseDto(MessageConstants.serverErrorMessage, null);
        }
    }


    /**
     * Creates an order Asynchronously
     *
     * @param orderDto The {@link OrderDto} having order info
     * @return {@link CreateResponseDto} having new id
     */

    @Async
    public CompletableFuture<CreateResponseDto> createOrderAsync(OrderDto orderDto) {

        logger.info("Creating order for customer : {}", orderDto.getCustomer().getId());
        try {

            //Extracting payment information to be sent to payment service
            PaymentInfo paymentInfo = PaymentInfoMapper.INSTANCE.getPaymentInfo(orderDto.getPaymentInfo());
            PaymentInfo newPayment = paymentRepository.save(paymentInfo);


            Orders newOrder = OrdersMapper.INSTANCE.getOrder(orderDto);
            newOrder.setPaymentInfo(newPayment);

            newOrder.setStatus(orderStatus.findByStatus(OrderStatusEnum.CREATED.name()));


            Orders createdOrder = orderRepository.save(newOrder);

            logger.info("Processing payment for order {} ", createdOrder.getId());

            //Call to payment service with payment Information. This service sets the status of the order to PAYMENT_PROCESSING before processing the payment
            // returns the confirmation number


            createdOrder.setStatus(orderStatus.findByStatus(OrderStatusEnum.COMPLETE.name()));
            orderRepository.save(createdOrder);


            logger.info("Created order : {} ", newOrder.getId());
            return CompletableFuture.completedFuture(new CreateResponseDto(MessageConstants.createMessage, newOrder.getId().toString()));
        } catch (Exception ex) {
            logger.error("Exception creating order {}", ex);
            return CompletableFuture.completedFuture(new CreateResponseDto(MessageConstants.serverErrorMessage, null));
        }

    }


}
