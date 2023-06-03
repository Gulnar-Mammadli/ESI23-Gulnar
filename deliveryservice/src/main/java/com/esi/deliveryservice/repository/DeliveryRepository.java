package com.esi.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.deliveryservice.model.Order;


public interface DeliveryRepository extends JpaRepository<Order, String> {
}
