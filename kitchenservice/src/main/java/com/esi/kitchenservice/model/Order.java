package com.esi.kitchenservice.model;

import com.esi.kitchenservice.dto.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "kitchenorderstable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private String userId;
    private String pizzaCode;
    private Integer pizzaQuantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}



