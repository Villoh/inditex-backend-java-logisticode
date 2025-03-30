package com.hackathon.inditex.controller;

import com.hackathon.inditex.model.dto.order.OrderCreatedDTO;
import com.hackathon.inditex.model.dto.order.OrderCreationDTO;
import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrdersListingDTO;
import com.hackathon.inditex.service.OrderService;
import com.hackathon.inditex.util.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing orders.
 */
@RestController
@RequestMapping(Constant.Api.ORDERS_ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates a new order.
     * @param orderCreationDTO the order data
     * @return the created order details
     */
    @PostMapping
    @Operation(
            summary = "Create an order",
            description = "Creates a new order with the given details",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created successfully"),
                    @ApiResponse(responseCode = "500", description = "Invalid input data")
            }
    )
    public ResponseEntity<OrderCreatedDTO> createOrder(@RequestBody OrderCreationDTO orderCreationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderCreationDTO));
    }

    /**
     * Retrieves all orders.
     * @return list of orders
     */
    @GetMapping
    @Operation(
            summary = "Get all orders",
            description = "Retrieves a list of all orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of orders retrieved successfully")
            }
    )
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * Assigns orders to centers.
     * @return processed orders listing
     */
    @PostMapping("/order-assignations")
    @Operation(
            summary = "Assign orders to centers",
            description = "Processes order assignations to centers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders assigned successfully")
            }
    )
    public ResponseEntity<ProcessedOrdersListingDTO> assignOrders(){
        return ResponseEntity.ok(orderService.asignCenters());
    }
}
