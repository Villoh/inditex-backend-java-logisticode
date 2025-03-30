package com.hackathon.inditex.repository;

import com.hackathon.inditex.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Order} entities.
 * Extends JpaRepository to provide basic CRUD operations.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds all orders with the specified status and orders them by ID in ascending order.
     *
     * @param status the status of the orders to be retrieved
     * @return a list of orders matching the given status, ordered by ID in ascending order
     */
    List<Order> findByStatusOrderByIdAsc(String status);
}