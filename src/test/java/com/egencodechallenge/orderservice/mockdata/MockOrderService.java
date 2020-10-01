package com.egencodechallenge.orderservice.mockdata;

import com.egencodechallenge.orderservice.constants.OrderStatusEnum;
import com.egencodechallenge.orderservice.models.Customer;
import com.egencodechallenge.orderservice.models.OrderStatus;
import com.egencodechallenge.orderservice.models.Orders;
import com.egencodechallenge.orderservice.models.PaymentInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Mocking the data to be used by the testing framework
 */
@Component
public class MockOrderService {

    public List<OrderStatus> getOrderStatuses() {

        List<OrderStatus> orderStatuses = new ArrayList<>();
        orderStatuses.add(new OrderStatus(1, OrderStatusEnum.CREATED.name()));
        orderStatuses.add(new OrderStatus(2, OrderStatusEnum.PAYMENT_PROCESSING.name()));
        orderStatuses.add(new OrderStatus(3, OrderStatusEnum.COMPLETE.name()));
        orderStatuses.add(new OrderStatus(4, OrderStatusEnum.CANCELLED.name()));
        return orderStatuses;
    }

    public List<Orders> getOrders() {
        List<Orders> orders = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setStatus(new OrderStatus(1, OrderStatusEnum.CREATED.name()));

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setId(1);


        order.setPaymentInfo(paymentInfo);

        orders.add(order);

        return orders;
    }

    public Optional<Orders> getOrderById(UUID orderId) {
        Orders order = new Orders();
        order.setId(orderId);
        return Optional.of(order);
    }
}
