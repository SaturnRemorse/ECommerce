package com.saturn.ecommerce.order_service.clients;

import com.saturn.ecommerce.order_service.dtos.OrderRequestDto;
import com.saturn.ecommerce.order_service.dtos.OrderRequestItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory-service", path="/inventory")
public interface InventoryFeignClient {

    @PutMapping("/products/reduce-stock")
    Double reduceStocks(OrderRequestDto orderRequestDto);
}
