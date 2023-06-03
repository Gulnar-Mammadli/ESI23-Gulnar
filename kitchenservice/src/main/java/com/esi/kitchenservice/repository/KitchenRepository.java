package com.esi.kitchenservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.kitchenservice.model.Order;

public interface KitchenRepository extends JpaRepository<Order, String> {
}
