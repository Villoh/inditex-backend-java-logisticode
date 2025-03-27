package com.hackathon.inditex.mapper;

import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderDTO orderToOrderDto(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .size(order.getSize())
                .status(order.getStatus())
                .assignedCenter(order.getAssignedCenter())
                .coordinates(order.getCoordinates())
                .build();
    }

    public List<OrderDTO> ordersToOrderDTOList(List<Order> orders){
        return orders.stream()
                .map(this::orderToOrderDto)
                .toList();
    }
}
