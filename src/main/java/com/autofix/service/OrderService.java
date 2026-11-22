package com.autofix.service;

import com.autofix.dto.CreateOrderRequest;
import com.autofix.dto.OrderResponse;
import com.autofix.dto.PublicOrderResponse;
import com.autofix.exception.ConflictException;
import com.autofix.exception.NotFoundException;
import com.autofix.model.Customer;
import com.autofix.model.Order;
import com.autofix.model.RepairStatus;
import com.autofix.model.Vehicle;
import com.autofix.repository.CustomerRepository;
import com.autofix.repository.OrderRepository;
import com.autofix.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
    public class OrderService {

        private CustomerRepository customerRepository;
        private OrderRepository orderRepository;
        private VehicleRepository vehicleRepository;
        private String Alphabet = "ABCDEFGHJKMNPQRSTUVWXYZ23456789";
        private SecureRandom random = new SecureRandom();

    public OrderService(CustomerRepository customerRepository, OrderRepository orderRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        String phoneNumber = request.getPhoneNumber().trim();
        String vin = request.getVin().trim().toUpperCase();
        String registrationNumber = request.getRegistrationNumber().trim().toUpperCase().replace(" ", "");

        Optional<Customer> found = customerRepository.findByPhoneNumber(phoneNumber);

        Customer customer;
        if (found.isPresent()) {
            customer = found.get();
        } else {
            customer = new Customer();
            customer.setName(request.getCustomerName());
            customer.setPhoneNumber(phoneNumber);
            customer = customerRepository.save(customer);
        }

        Optional<Vehicle> foundVehicle = vehicleRepository.findByVin(vin);

        Vehicle vehicle;
        if (foundVehicle.isPresent()) {
            vehicle = foundVehicle.get();
            if (!vehicle.getCustomer().getId().equals(customer.getId())) {
                throw new ConflictException("The vehicle with this VIN is registered to another customer");
            }
        } else {
            if (vehicleRepository.findByRegistrationNumber(registrationNumber).isPresent()) {
                throw new ConflictException("This registration number is already used");
            }
            vehicle = new Vehicle();
            vehicle.setBrand(request.getBrand());
            vehicle.setModel(request.getModel());
            vehicle.setVin(vin);
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setCustomer(customer);
            vehicle = vehicleRepository.save(vehicle);
        }

        Order order = new Order();
        order.setVehicle(vehicle);
        order.setMileage(request.getMileage());
        order.setFaultDescription(request.getFaultDescription());
        order.setAccessCode(generateUniqueCode());
        order = orderRepository.save(order);

        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        Vehicle vehicle = order.getVehicle();
        Customer customer = vehicle.getCustomer();

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setAccessCode(order.getAccessCode());
        response.setStatus(order.getStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setMileage(order.getMileage());
        response.setFaultDescription(order.getFaultDescription());
        response.setCustomerName(customer.getName());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setBrand(vehicle.getBrand());
        response.setModel(vehicle.getModel());
        response.setVin(vehicle.getVin());
        response.setRegistrationNumber(vehicle.getRegistrationNumber());
        return response;
    }

    private PublicOrderResponse toPublicResponse(Order order) {
        PublicOrderResponse response = new PublicOrderResponse();
        response.setStatus(order.getStatus());
        response.setBrand(order.getVehicle().getBrand());
        response.setModel(order.getVehicle().getModel());
        response.setFaultDescription(order.getFaultDescription());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        return response;
    }

    @Transactional
    public List<OrderResponse> getOrders(RepairStatus status) {
        List<Order> orders;
        if (status == null) {
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findByStatus(status);
        }

        List<OrderResponse> result = new ArrayList<>();
        for (Order order : orders) {
            result.add(toResponse(order));

        }
        return result;
    }

    @Transactional
    public OrderResponse changeStatus(Long id, RepairStatus status) {

        Optional<Order> found = orderRepository.findById(id);

        if (found.isPresent()) {
            Order order = found.get();
            order.setStatus(status);
            order = orderRepository.save(order);
            return toResponse(order);
        } else {
            throw new NotFoundException("Order with id " + id + " not found");
        }
    }

    @Transactional
    public PublicOrderResponse trackOrder(String accessCode) {

        Optional<Order> found = orderRepository.findByAccessCode(accessCode.trim().toUpperCase());

        if (found.isPresent()) {
            Order order = found.get();
            return toPublicResponse(order);
        } else {
            throw new NotFoundException("Order with this access code " + accessCode + " not found");
        }
    }

    private String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(Alphabet.length());
            char symbol = Alphabet.charAt(index);
            code.append(symbol);
        }
        return code.toString();
    }

    private String generateUniqueCode() {
        String code;
        do {
            code = generateCode();
        } while (orderRepository.findByAccessCode(code).isPresent());
        return code;


    }

}