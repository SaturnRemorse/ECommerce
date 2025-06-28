package com.saturn.ecommerce.order_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private Long id;
    private List<OrderRequestItemDto> items;
    private Double totalPrice;
}
