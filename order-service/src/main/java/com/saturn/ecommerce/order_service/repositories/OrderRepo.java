package com.saturn.ecommerce.order_service.repositories;

import com.saturn.ecommerce.order_service.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {
}
