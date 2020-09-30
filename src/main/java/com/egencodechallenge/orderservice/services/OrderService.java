package com.egencodechallenge.orderservice.services;

import com.egencodechallenge.orderservice.constants.MessageConstants;
import com.egencodechallenge.orderservice.constants.OrderStatusEnum;
import com.egencodechallenge.orderservice.dtos.CreateResponseDto;
import com.egencodechallenge.orderservice.dtos.OrderDto;
import com.egencodechallenge.orderservice.mappers.OrdersMapper;
import com.egencodechallenge.orderservice.mappers.PaymentInfoMapper;
import com.egencodechallenge.orderservice.models.Orders;
import com.egencodechallenge.orderservice.models.PaymentInfo;
import com.egencodechallenge.orderservice.repository.OrderRepository;
import com.egencodechallenge.orderservice.repository.OrderStatusRepository;
import com.egencodechallenge.orderservice.repository.PaymentInfoRepository;
import com.egencodechallenge.orderservice.utils.OrderServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service manage orders
 */
@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentInfoRepository paymentRepository;

    @Autowired
    private OrderServiceUtil orderServiceUtil;

    @Autowired
    private OrderStatusRepository orderStatus;


    /***
     * Retrieves specific order based on the Id specified
     * @param orderId
     * @return {@link Optional<OrderDto>}
     */
    public Optional<OrderDto> getOrderById(UUID orderId) {
        logger.info("Getting order {}", orderId);


        Optional<Orders> order = orderRepository.findById(orderId);

        return order.map(OrdersMapper.INSTANCE::generateOrderDto);

    }

    /**
     * Creates an order
     *
     * @param orderDto The {@link OrderDto} having order info
     * @return {@link CreateResponseDto} having new id
     */
    public CreateResponseDto createOrder(OrderDto orderDto) {
        try {

            if (!orderServiceUtil.validateOrder(orderDto)) {
                //Todo : Handle invalid order response

                return new CreateResponseDto(MessageConstants.INVALID_ORDER, null);
            }

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
            return new CreateResponseDto(MessageConstants.CREATED_ENITY, newOrder.getId().toString());
        } catch (Exception ex) {
            logger.error("Exception creating order {}", ex);
            return new CreateResponseDto(MessageConstants.SERVER_ERROR, null);
        }
    }


}
