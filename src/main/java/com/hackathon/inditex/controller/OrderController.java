package com.hackathon.inditex.controller;

import com.hackathon.inditex.model.dto.order.OrderCreatedDTO;
import com.hackathon.inditex.model.dto.order.OrderCreationDTO;
import com.hackathon.inditex.model.dto.order.OrderDTO;
import com.hackathon.inditex.model.dto.order.ProcessedOrdersListingDTO;
import com.hackathon.inditex.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderCreatedDTO> createOrder(@RequestBody OrderCreationDTO orderCreationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderCreationDTO));
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/order-assignations")
    public ResponseEntity<ProcessedOrdersListingDTO> assignOrders(){
        return ResponseEntity.ok(orderService.asignCenters());
    }
}
