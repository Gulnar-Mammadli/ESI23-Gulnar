package com.esi.deliveryservice.service;


import com.esi.deliveryservice.dto.OrderDto;
import com.esi.deliveryservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.deliveryservice.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @KafkaListener(topics = "orderReadyTopic", groupId = "deliveryGroup")
    private void getKitchenOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .pizzaCode(orderDto.getPizzaCode())
                .pizzaQuantity(orderDto.getPizzaQuantity())
                .orderStatus(orderDto.getOrderStatus())
                .build();
        deliveryRepository.save(order);
    }


}

