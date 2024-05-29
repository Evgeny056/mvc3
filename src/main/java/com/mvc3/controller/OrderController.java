package com.mvc3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc3.model.entity.Order;
import com.mvc3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody String orderJson) {
        try {
            Order order = new ObjectMapper().readValue(orderJson, Order.class);
            orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order added successfully");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format for order");
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<String> getOrder(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        try {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Error converting order to JSON");
        }
    }

}
