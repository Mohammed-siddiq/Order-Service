package com.egencodechallenge.orderservice;

import com.egencodechallenge.orderservice.constants.MessageConstants;
import com.egencodechallenge.orderservice.dtos.OrderDto;
import com.egencodechallenge.orderservice.dtos.ResponseDto;
import com.egencodechallenge.orderservice.mockdata.MockOrderService;
import com.egencodechallenge.orderservice.repository.OrderRepository;
import com.egencodechallenge.orderservice.repository.OrderStatusRepository;
import com.egencodechallenge.orderservice.repository.PaymentInfoRepository;
import com.egencodechallenge.orderservice.services.OrderService;
import com.egencodechallenge.orderservice.utils.OrderServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class OrdersServiceApplicationTests {


    @MockBean
    OrderRepository orderRepository;
    @MockBean
    PaymentInfoRepository paymentInfoRepository;
    @MockBean
    OrderStatusRepository orderStatusRepository;
    @MockBean
    OrderServiceUtil orderServiceUtil;


    private OrderService orderService;

    @Autowired
    private MockOrderService mockOrderService;


    @Test
    public void testCreateOrder() {
        orderService = new OrderService(orderRepository, paymentInfoRepository, orderServiceUtil, orderStatusRepository);
        OrderDto orderDto = createOrderDto();
        ResponseDto response = orderService.createOrder(orderDto);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getMessage(), MessageConstants.INVALID_ORDER);

    }

    @Test
    void testGetOrderById() {
        orderService = new OrderService(orderRepository, paymentInfoRepository, orderServiceUtil, orderStatusRepository);
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(mockOrderService.getOrderById(orderId));
        Optional<OrderDto> response = orderService.getOrderById(orderId);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(response.get().getOrderId(),orderId);
    }

    private OrderDto createOrderDto() {
        return new OrderDto();
    }


}
