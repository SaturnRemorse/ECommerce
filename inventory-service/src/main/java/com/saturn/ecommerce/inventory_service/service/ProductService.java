package com.saturn.ecommerce.inventory_service.service;


import com.saturn.ecommerce.inventory_service.dto.OrderRequestDto;
import com.saturn.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.saturn.ecommerce.inventory_service.dto.ProductDto;
import com.saturn.ecommerce.inventory_service.entity.Product;
import com.saturn.ecommerce.inventory_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public List<ProductDto> getAllInventory(){
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(products -> mapper.map(products, ProductDto.class)).toList();
    }

    public ProductDto getProductById(Long id){
        log.info("Fetching Product By id : "+id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not Found"));
        return mapper.map(product, ProductDto.class);
    }


    @Transactional
    public Double reduceStock(OrderRequestDto order){
        log.info("reducing stocks");
        Double total_price = 0.0;
        for(OrderRequestItemDto orderRequestItemDto: order.getItems()){
           Long pId = orderRequestItemDto.getPId();
           Integer quantity = orderRequestItemDto.getQuantity();

           Product product = productRepository.findById(pId).orElseThrow(()->
           new RuntimeException("Product does not exits"));
           if(product.getStock()<quantity){
               throw new RuntimeException("Product not in stock");
           }
           product.setStock(product.getStock()-quantity);
           total_price+=quantity*product.getPrice();
        }
        return total_price;
    }

}
