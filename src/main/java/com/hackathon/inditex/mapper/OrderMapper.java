package com.hackathon.inditex.mapper;

import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper class for converting Order entities to OrderDTOs and vice versa.
 */
@Component
public class OrderMapper {

    /**
     * Converts an Order entity to an OrderDTO.
     *
     * @param order the Order entity to convert
     * @return the corresponding OrderDTO
     */
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

    /**
     * Converts a list of Order entities to a list of OrderDTOs.
     *
     * @param orders the list of Order entities to convert
     * @return a list of corresponding OrderDTOs
     */
    public List<OrderDTO> ordersToOrderDTOList(List<Order> orders){
        return orders.stream()
                .map(this::orderToOrderDto)
                .toList();
    }
}