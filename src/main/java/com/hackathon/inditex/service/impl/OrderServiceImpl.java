package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.mapper.OrderMapper;
import com.hackathon.inditex.model.dto.order.*;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.model.entity.Order;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.repository.OrderRepository;
import com.hackathon.inditex.service.OrderService;
import com.hackathon.inditex.util.Constant;
import com.hackathon.inditex.validation.util.GeoUtil;
import com.hackathon.inditex.validation.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link OrderService} interface.
 * Handles order creation, retrieval, and assignment to centers.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CenterRepository centerRepository;
    private final GeoUtil geoUtil;


    /**
     * Creates a new order and saves it to the repository.
     *
     * @param orderCreationDTO The DTO containing order details.
     * @return The created order details.
     */
    @Override
    public OrderCreatedDTO createOrder(OrderCreationDTO orderCreationDTO) {
        Order newOrder = Order.builder()
                .customerId(orderCreationDTO.getCustomerId())
                .size(orderCreationDTO.getSize())
                .status(Constant.Order.PENDING_STATUS)
                .coordinates(orderCreationDTO.getCoordinates())
                .build();

        orderRepository.save(newOrder);

        return new OrderCreatedDTO(newOrder.getId(), newOrder.getCustomerId(), newOrder.getSize(),
                newOrder.getAssignedCenter(), newOrder.getCoordinates(), newOrder.getStatus(), Constant.Order.ORDER_CREATED);
    }

    /**
     * Retrieves all orders.
     *
     * @return A list of all orders in DTO format.
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.ordersToOrderDTOList(orderRepository.findAll());
    }

    /**
     * Assigns centers to pending orders.
     *
     * @return A DTO containing a list of processed orders.
     */
    @Override
    public ProcessedOrdersListingDTO asignCenters() {
        List<ProcessedOrderDTO> processedOrders = orderRepository.findByStatusOrderByIdAsc(Constant.Order.PENDING_STATUS)
                .stream()
                .map(this::processOrder)
                .toList();

        return new ProcessedOrdersListingDTO(processedOrders);
    }

    /**
     * Processes an order by finding the closest available center.
     *
     * @param order The order to process.
     * @return The processed order DTO, either assigned or failed.
     */
    private ProcessedOrderDTO processOrder(Order order) {
        List<Center> closestCenters = centerRepository.findClosestCenter(order.getCoordinates().getLatitude(),
                order.getCoordinates().getLongitude(), order.getSize());

        return closestCenters.isEmpty()
                ? new ProcessedOrderDTO(null, order.getId(), null, order.getStatus(), Constant.Order.NO_AVAILABLE_CENTERS)
                : findAvailableCenter(order, closestCenters).orElseGet(() -> new ProcessedOrderDTO(null, order.getId(), null, order.getStatus(), Constant.Order.ALL_CENTERS_FULL));
    }

    /**
     * Assigns an order to a given center and updates the repository.
     *
     * @param order The order to assign.
     * @param center The center where the order is assigned.
     * @return The DTO containing processed order details.
     */
    private ProcessedOrderDTO assignOrderToCenter(Order order, Center center) {
        double distance = geoUtil.calculateDistance(
                order.getCoordinates().getLatitude(), order.getCoordinates().getLongitude(),
                center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude()
        );

        order.setStatus(Constant.Order.ASSIGNED_STATUS);
        orderRepository.save(order);

        center.setCurrentLoad(center.getCurrentLoad() + 1);
        centerRepository.save(center);

        return new ProcessedOrderDTO(distance, order.getId(), center.getName(), order.getStatus(), null);
    }

    /**
     * Finds an available center for the given order.
     *
     * @param order The order to assign.
     * @param centers The list of centers to check.
     * @return An optional containing the processed order DTO if a center is found.
     */
    private Optional<ProcessedOrderDTO> findAvailableCenter(Order order, List<Center> centers) {
        return centers.stream()
                .filter(center -> ValidationUtil.isSmallerThan(center.getCurrentLoad(), center.getMaxCapacity()))
                .findFirst()
                .map(center -> assignOrderToCenter(order, center));
    }
}