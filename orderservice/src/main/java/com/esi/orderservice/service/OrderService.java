package com.esi.orderservice.service;

import com.esi.orderservice.model.Order;
import com.esi.orderservice.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.orderservice.dto.OrderDto;
import com.esi.orderservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderDto> kafkaTemplate;



    public void addOrder(OrderDto orderDto) {

        Order order = Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .pizzaCode(orderDto.getPizzaCode())
                .pizzaQuantity(orderDto.getPizzaQuantity())
                .address(orderDto.getAddress())
                .build();
        order.setOrderStatus(OrderStatus.Received);
        orderDto.setOrderStatus(OrderStatus.Received);

        orderRepository.save(order);
        kafkaTemplate.send("orderCreatedTopic",orderDto);
    }

}
