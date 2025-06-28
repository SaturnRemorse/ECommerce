package com.saturn.ecommerce.inventory_service.controller;

import com.saturn.ecommerce.inventory_service.dto.ProductDto;
import com.saturn.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllInventory(), HttpStatus.OK);
    }

    @GetMapping("/{pId}")
    public ResponseEntity<ProductDto> getProductId(@PathVariable Long pId){
        return new ResponseEntity<>(productService.getProductById(pId), HttpStatus.OK);
    }
}
