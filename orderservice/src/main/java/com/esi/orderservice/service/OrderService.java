package com.esi.orderservice.service;

import com.esi.orderservice.model.Order;
import com.esi.orderservice.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.esi.orderservice.dto.OrderDto;
import com.esi.orderservice.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderDto> kafkaOrderTemplate;



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
        kafkaOrderTemplate.send("orderCreatedTopic",orderDto);
        log.info("A order request id: {} has been added and sent to kitchen service", orderDto.getId());
    }

    public String getAddressById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(Order::getAddress).orElse(null);
    }
}
