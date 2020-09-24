package com.egencodechallenge.orderservice.utils;

import com.egencodechallenge.orderservice.dtos.OrderDto;
import org.springframework.stereotype.Component;


/**
 * Utilities related to the order service
 */

@Component
public class OrderServiceUtil {


    /**
     * Validate the given order
     * @param order {{@link OrderDto}} to be validated
     * @return True if the order is valid
     */
    private boolean validateOrder(OrderDto order){


        return true;
    }
}
