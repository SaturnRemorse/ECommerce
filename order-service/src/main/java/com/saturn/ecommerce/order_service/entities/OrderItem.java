package com.saturn.ecommerce.order_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_id")
    private Long pId;

    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Orders order;
}
