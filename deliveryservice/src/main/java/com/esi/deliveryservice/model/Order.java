package com.esi.deliveryservice.model;

import com.esi.deliveryservice.dto.OrderStatus;

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
@Table(name = "orderdelivery")
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



