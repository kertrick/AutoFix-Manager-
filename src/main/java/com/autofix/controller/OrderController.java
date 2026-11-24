package com.autofix.controller;

import com.autofix.dto.ChangeStatusRequest;
import com.autofix.dto.CreateOrderRequest;
import com.autofix.dto.OrderResponse;
import com.autofix.model.Order;
import com.autofix.model.RepairStatus;
import com.autofix.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public List<OrderResponse> getOrders(@RequestParam(required = false) RepairStatus status) {
        return orderService.getOrders(status);
    }

    @PatchMapping("/{id}/status")
    public OrderResponse changeStatus(@PathVariable Long id, @Valid @RequestBody ChangeStatusRequest request) {
        return orderService.changeStatus(id, request.getStatus());
    }

}
