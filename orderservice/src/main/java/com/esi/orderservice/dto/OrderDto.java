package com.esi.orderservice.dto;

import com.esi.orderservice.model.OrderStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String id;
    private String userId;
    private String pizzaCode;
    private Integer pizzaQuantity;
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}



