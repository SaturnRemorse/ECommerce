package com.saturn.ecommerce.order_service.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class OrderRequestItemDto {
    private Long id;
    private Long pId;
    private Integer quantity;
}
