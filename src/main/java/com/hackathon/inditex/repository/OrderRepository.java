package com.hackathon.inditex.repository;

import com.hackathon.inditex.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatusOrderByIdAsc(String status);
}