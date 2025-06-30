package com.saturn.ecommerce.inventory_service.dto;

import lombok.Data;

@Data
public class OrderRequestItemDto {
    private Long pId;
    private Integer quantity;
}
