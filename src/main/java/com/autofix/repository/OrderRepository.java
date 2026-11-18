package com.autofix.repository;

import com.autofix.model.Order;
import com.autofix.model.RepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByAccessCode(String accessCode);
    List<Order> findByStatus(RepairStatus status);
    List<Order> findByVehicleVinOrderByCreatedAtDesc(String vin);
}
