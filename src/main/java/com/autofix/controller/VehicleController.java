package com.autofix.controller;

import com.autofix.dto.VehicleHistoryResponse;
import com.autofix.service.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/search")
    public VehicleHistoryResponse vehicleSearch(@RequestParam String q) {
        return vehicleService.findVehicleHistory(q);
    }
}
