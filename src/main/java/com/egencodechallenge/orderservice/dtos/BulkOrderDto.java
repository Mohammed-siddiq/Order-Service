package com.egencodechallenge.orderservice.dtos;

import lombok.Data;

import java.util.List;

/**
 * Data transfer Object for bulk order request
 */
@Data
public class BulkOrderDto {

    long clientId;
    List<OrderDto> orders;
}
