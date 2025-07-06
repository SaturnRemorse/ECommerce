package com.saturn.ecommerce.order_service.controller;

import com.saturn.ecommerce.order_service.clients.InventoryFeignClient;
import com.saturn.ecommerce.order_service.configs.FeatureConfig;
import com.saturn.ecommerce.order_service.dtos.OrderRequestDto;
import com.saturn.ecommerce.order_service.service.OrderService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path= "/core")
@RefreshScope
public class OrderController {

    private final OrderService orderService;
    private final FeatureConfig featureConfig;

    @Value("${my.variable}")
    private String myVariable;


    @GetMapping(path = "/helloOrders")
    //public String helloOrder(@RequestHeader("X-USER-ID") Long userId){
    public String helloOrder(){
        if(featureConfig.isUserTrackingAvailable()){
            return "user tracking available";
        }
        else{
            return "user tracking not available";
        }


    }


    @GetMapping(path = "/")
    public ResponseEntity<List<OrderRequestDto>> getOrders(){
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping(path = "/{oId}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long oId){
        return new ResponseEntity<>(orderService.getOrderById(oId), HttpStatus.OK);
    }

    @PostMapping(path = "/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        log.info(orderRequestDto.toString());
        OrderRequestDto createdOrder = orderService.createOrderRequest(orderRequestDto);
        return ResponseEntity.ok(createdOrder);
    }
}
