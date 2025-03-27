package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.mapper.OrderMapper;
import com.hackathon.inditex.model.dto.order.OrderCreatedDTO;
import com.hackathon.inditex.model.dto.order.OrderCreationDTO;
import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrderDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrderFailedDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrdersListingDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.model.entity.Order;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.repository.OrderRepository;
import com.hackathon.inditex.service.OrderService;
import com.hackathon.inditex.util.Constant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CenterRepository centerRepository;

    private static final double EARTH_RADIUS_KM = 6371.0; // Radius of Earth in kilometers


    @Override
    public OrderCreatedDTO createOrder(OrderCreationDTO orderCreationDto) {
        Order newOrder = Order.builder()
                .customerId(orderCreationDto.getCustomerId())
                .size(orderCreationDto.getSize())
                .status(Constant.Order.PENDING_STATUS)
                .coordinates(orderCreationDto.getCoordinates())
                .build();

        orderRepository.save(newOrder);

        return new OrderCreatedDTO(newOrder.getId(), newOrder.getCustomerId(), newOrder.getSize(),
                newOrder.getAssignedCenter(), newOrder.getCoordinates(), newOrder.getStatus(), Constant.Order.ORDER_CREATED);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.ordersToOrderDTOList(orderRepository.findAll());
    }

    @Override
    @Transactional
    public ProcessedOrdersListingDTO asignCenters() {
        List<ProcessedOrderDTO> processedOrders = orderRepository.findByStatusOrderByIdAsc(Constant.Order.PENDING_STATUS)
                .stream()
                .map(this::processOrder)
                .toList();

        return new ProcessedOrdersListingDTO(processedOrders);
    }

    /**
     * Processes the orders that have PENDING status.
     *
     * @param order The order to process
     *
     * @return ProcessedOrderDto A DTO with the details of the processed order
     *
     * */
    private ProcessedOrderDTO processOrder(Order order) {

        List<Center> closestCenters = centerRepository.findClosestCenter(order.getCoordinates().getLatitude(),
                order.getCoordinates().getLongitude(), order.getSize());

        if(closestCenters.isEmpty()){
            return createFailedOrder(order, Constant.Order.NO_AVAILABLE_CENTERS);
        }

        return findAvailableCenter(order, closestCenters).orElseGet(() -> createFailedOrder(order, Constant.Order.ALL_CENTERS_FULL));
    }

    /**
     * Assigns an order to a center
     *
     * @param center Center to which the order will be assigned to
     * @param order Order to assign
     *
     * @return ProcessedOrderDto The dto containing the details of the assigned order.
     *
     * */
    private ProcessedOrderDTO assignOrderToCenter(Order order, Center center) {
        double distance = calculateDistance(
                order.getCoordinates().getLatitude(), order.getCoordinates().getLongitude(),
                center.getCoordinates().getLatitude(), center.getCoordinates().getLongitude()
        );

        order.setStatus(Constant.Order.ASSIGNED_STATUS);
        orderRepository.save(order);

        center.setCurrentLoad(center.getCurrentLoad() + 1);
        centerRepository.save(center);

        return new ProcessedOrderDTO(distance, order.getId(), center.getName(), order.getStatus());
    }

    private ProcessedOrderFailedDTO createFailedOrder(Order order, String reason) {
        return new ProcessedOrderFailedDTO(null, order.getId(), null, order.getStatus(), reason);
    }

    private Optional<ProcessedOrderDTO> findAvailableCenter(Order order, List<Center> centers){
        return centers.stream()
                .filter(center -> center.getCurrentLoad() < center.getMaxCapacity())
                .findFirst()
                .map(center -> assignOrderToCenter(order,center));
    }

    /**
     * @param lat1 Latitude of first a pair of coordinates
     * @param lon1 Longitude of first a pair of coordinates
     * @param lat2 Latitude of second pair of coordinates
     * @param lon2 Longitude of second pair of coordinates
     *
     * @return distance The distance from coordinates lat1, lon1 to lat2,lon2
     *
     * */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {


        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);


        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));


        return EARTH_RADIUS_KM * c;
    }
}
