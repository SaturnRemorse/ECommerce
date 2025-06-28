package com.saturn.ecommerce.order_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestItemDto {
    private Long id;
    private Long pId;
    private Integer quantity;
}
