package com.saturn.ecommerce.inventory_service.controller;

import com.saturn.ecommerce.inventory_service.clients.OrderFeignClient;
import com.saturn.ecommerce.inventory_service.dto.OrderRequestDto;
import com.saturn.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.saturn.ecommerce.inventory_service.dto.ProductDto;
import com.saturn.ecommerce.inventory_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrderFeignClient orderFeignClient;


    @GetMapping("/fetchorders")
    public String fetchOrders(HttpServletRequest httpServletRequest){
        log.info(httpServletRequest.getHeader("x-custom-header"));
//        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service").getFirst();
//        return restClient.get().uri(serviceInstance.getUri()+"/orders/core/helloOrders")
//                .retrieve()
//                .body(String.class);
        return orderFeignClient.helloOrders();
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllInventory(), HttpStatus.OK);
    }

    @GetMapping("/{pId}")
    public ResponseEntity<ProductDto> getProductId(@PathVariable Long pId){
        return new ResponseEntity<>(productService.getProductById(pId), HttpStatus.OK);
    }

    @PutMapping("/reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto order){
        log.info(order.toString());
        Double amount = productService.reduceStock(order);
        return ResponseEntity.ok(amount);
    }

}
