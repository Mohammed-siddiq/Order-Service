package com.egencodechallenge.orderservice.services;

import com.egencodechallenge.orderservice.constants.MessageConstants;
import com.egencodechallenge.orderservice.constants.OrderStatusEnum;
import com.egencodechallenge.orderservice.dtos.ResponseDto;
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

    private final OrderRepository orderRepository;

    private final PaymentInfoRepository paymentRepository;

    private final OrderServiceUtil orderServiceUtil;

    private final OrderStatusRepository orderStatus;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentInfoRepository paymentRepository, OrderServiceUtil orderServiceUtil, OrderStatusRepository orderStatus) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.orderServiceUtil = orderServiceUtil;
        this.orderStatus = orderStatus;
    }


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

    public ResponseDto cancelOrder(UUID orderId) {

        logger.info("Cancelling order {}", orderId);

        Optional<Orders> order = orderRepository.findById(orderId);

        if (order.isPresent()) {
            if (order.get().getStatus().getStatus().equals(OrderStatusEnum.COMPLETE.name())) {
                return new ResponseDto(MessageConstants.ORDER_ALREADY_COMPLETED);
            }

            logger.debug("Updating status to cancel");
            order.get().setStatus(orderStatus.findByStatus(OrderStatusEnum.CANCELLED.name()));
            orderRepository.save(order.get());
            return new ResponseDto(MessageConstants.SUCCESSFULLY_CANCELED + orderId);

        }
        return (new ResponseDto(MessageConstants.NO_SUCH_ORDER + orderId));

    }

    /**
     * Creates an order
     *
     * @param orderDto The {@link OrderDto} having order info
     * @return {@link ResponseDto} having new id
     */
    public ResponseDto createOrder(OrderDto orderDto) {
        try {

            if (!orderServiceUtil.validateOrder(orderDto)) {
                //Todo : Handle invalid order response

                return new ResponseDto(MessageConstants.INVALID_ORDER);
            }

            //Extracting payment information to be sent to payment service
            PaymentInfo paymentInfo = PaymentInfoMapper.INSTANCE.getPaymentInfo(orderDto.getPaymentInfo());
            PaymentInfo newPayment = paymentRepository.save(paymentInfo);


            Orders newOrder = OrdersMapper.INSTANCE.getOrder(orderDto);
            newOrder.setPaymentInfo(newPayment);

            newOrder.setStatus(orderStatus.findByStatus(OrderStatusEnum.CREATED.name()));


            Orders createdOrder = orderRepository.save(newOrder);

            logger.info("Processing payment for order {} ", createdOrder.getId());

            //Call to 'payment service' with payment Information.
            //
            // This service sets the status of the order to PROCESSING before processing the payment and updates the
            // the confirmation number


//            orderRepository.save(createdOrder);


            logger.info("Created order : {} ", newOrder.getId());
            logger.info("Creating order for customer : {}", orderDto.getCustomer().getId());
            return new ResponseDto(MessageConstants.CREATED_ENTITY, newOrder.getId().toString());
        } catch (Exception ex) {
            logger.error("Exception creating order {}", ex);
            return new ResponseDto(MessageConstants.SERVER_ERROR);
        }
    }


}
