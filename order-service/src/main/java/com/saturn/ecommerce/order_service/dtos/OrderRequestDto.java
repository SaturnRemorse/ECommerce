package com.saturn.ecommerce.order_service.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class OrderRequestDto {

    private Long id;
    private List<OrderRequestItemDto> items;
    private Double totalPrice;
}
