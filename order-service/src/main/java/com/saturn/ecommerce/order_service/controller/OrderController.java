package com.saturn.ecommerce.order_service.controller;

import com.saturn.ecommerce.order_service.dtos.OrderRequestDto;
import com.saturn.ecommerce.order_service.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path= "/core")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<OrderRequestDto>> getOrders(){
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/{oId}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long oId){
        return new ResponseEntity<>(orderService.getOrderById(oId), HttpStatus.OK);
    }
}
