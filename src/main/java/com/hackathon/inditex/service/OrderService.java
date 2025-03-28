package com.hackathon.inditex.service;

import com.hackathon.inditex.model.dto.order.OrderCreatedDTO;
import com.hackathon.inditex.model.dto.order.OrderCreationDTO;
import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrdersListingDTO;

import java.util.List;

/**
 * Service interface for managing orders.
 * This interface defines methods for creating orders, retrieving orders, and assigning centers to orders.
 */
public interface OrderService {

    /**
     * Creates a new order based on the provided order creation data.
     *
     * @param orderCreationDto A DTO containing the details for creating a new order.
     * @return An {@link OrderCreatedDTO} containing the details of the created order,
     *         including its ID and creation status.
     */
    OrderCreatedDTO createOrder(OrderCreationDTO orderCreationDto);

    /**
     * Retrieves all existing orders.
     *
     * @return A list of {@link OrderDTO} containing the details of all orders.
     */
    List<OrderDTO> getAllOrders();

    /**
     * Assigns centers to the orders based on available centers.
     *
     * @return A {@link ProcessedOrdersListingDTO} containing the result of the assignment process,
     *         including the list of orders with their assigned centers and any processing messages.
     */
    ProcessedOrdersListingDTO asignCenters();
}
