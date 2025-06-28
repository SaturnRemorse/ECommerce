package com.saturn.ecommerce.inventory_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Double price;

    private Integer stock;


}
