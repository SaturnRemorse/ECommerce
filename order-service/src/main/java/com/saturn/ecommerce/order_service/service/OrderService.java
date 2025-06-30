package com.saturn.ecommerce.order_service.service;


import com.saturn.ecommerce.order_service.clients.InventoryFeignClient;
import com.saturn.ecommerce.order_service.dtos.OrderRequestDto;
import com.saturn.ecommerce.order_service.dtos.OrderRequestItemDto;
import com.saturn.ecommerce.order_service.entities.OrderItem;
import com.saturn.ecommerce.order_service.entities.OrderStatus;
import com.saturn.ecommerce.order_service.entities.Orders;
import com.saturn.ecommerce.order_service.repositories.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepo orderRepo;
    private final ModelMapper mapper;
    private final InventoryFeignClient inventoryFeignClient;

    public List<OrderRequestDto> getAllOrder(){
        log.info("Fetching all orders");
        List<Orders> orders = orderRepo.findAll();
        return orders.stream()
                .map(order -> mapper.map(order, OrderRequestDto.class)).toList();


    }

    public OrderRequestDto getOrderById(Long id){
        log.info("Fetching order with Id: {}", id);
        Orders order = orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order Not Found"));
        return mapper.map(order, OrderRequestDto.class);
    }

    public OrderRequestDto createOrderRequest(OrderRequestDto orderRequestDto) {
        Double totalPrice = inventoryFeignClient.reduceStocks(orderRequestDto);

        Orders orders = mapper.map(orderRequestDto, Orders.class);
        for(OrderItem orderItem: orders.getItems()){
            orderItem.setOrder(orders);
        }

        orders.setTotalPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        Orders savedOrder = orderRepo.save(orders);
        return mapper.map(savedOrder, OrderRequestDto.class);


    }
}
