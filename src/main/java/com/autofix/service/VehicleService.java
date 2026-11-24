package com.autofix.service;


import com.autofix.dto.OrderResponse;
import com.autofix.dto.VehicleHistoryResponse;
import com.autofix.exception.NotFoundException;
import com.autofix.model.Order;
import com.autofix.model.Vehicle;
import com.autofix.repository.OrderRepository;
import com.autofix.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;
    private OrderRepository orderRepository;
    private OrderService orderService;

    public VehicleService(VehicleRepository vehicleRepository, OrderRepository orderRepository, OrderService orderService) {
        this.vehicleRepository = vehicleRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @Transactional
    public VehicleHistoryResponse findVehicleHistory(String query) {
        String normal = query.trim().toUpperCase().replace(" ", "");

        Optional<Vehicle> byVin = vehicleRepository.findByVin(normal);

        Vehicle vehicle;
        if (byVin.isPresent()) {
            vehicle = byVin.get();
        } else {
            Optional<Vehicle> byPlate = vehicleRepository.findByRegistrationNumber(normal);
            if (byPlate.isPresent()) {
                vehicle = byPlate.get();
            } else {
                throw new NotFoundException("vehicle not found");
            }
        }

        List<Order> orders = orderRepository.findByVehicleVinOrderByCreatedAtDesc(vehicle.getVin());

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            orderResponses.add(orderService.toResponse(order));
        }

        VehicleHistoryResponse response = new VehicleHistoryResponse();
        response.setBrand(vehicle.getBrand());
        response.setModel(vehicle.getModel());
        response.setVin(vehicle.getVin());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        response.setOrders(orderResponses);

        return response;
    }

}
