package com.esi.kitchenservice.service;

import com.esi.kitchenservice.dto.OrderStatus;
import com.esi.kitchenservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.esi.kitchenservice.dto.OrderDto;
import com.esi.kitchenservice.repository.KitchenRepository;


@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    @KafkaListener(topics = "orderCreatedTopic", groupId = "kitchenGroup")
    private void receiveOrder(OrderDto orderDto){
        Order order = Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .pizzaCode(orderDto.getPizzaCode())
                .pizzaQuantity(orderDto.getPizzaQuantity())
                .orderStatus(orderDto.getOrderStatus())
                .build();
        kitchenRepository.save(order);
    }

    public void orderReady(OrderDto orderDto) {

    }
}

