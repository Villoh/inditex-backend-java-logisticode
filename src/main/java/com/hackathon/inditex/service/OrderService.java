package com.hackathon.inditex.service;

import com.hackathon.inditex.model.dto.order.OrderCreatedDTO;
import com.hackathon.inditex.model.dto.order.OrderCreationDTO;
import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrdersListingDTO;

import java.util.List;

public interface OrderService {
    OrderCreatedDTO createOrder(OrderCreationDTO orderCreationDto);
    List<OrderDTO> getAllOrders();
    ProcessedOrdersListingDTO asignCenters();
}
