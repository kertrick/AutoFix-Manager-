package com.autofix.controller;

import com.autofix.dto.PublicOrderResponse;
import com.autofix.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicTrackingController {

    private OrderService orderService;

    public PublicTrackingController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/track/{accessCode}")
    public PublicOrderResponse trackOrder(@PathVariable String accessCode) {
        return orderService.trackOrder(accessCode);
    }

}
