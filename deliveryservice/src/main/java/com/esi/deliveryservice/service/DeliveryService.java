package com.esi.deliveryservice.service;


import com.esi.deliveryservice.dto.OrderDto;
import com.esi.deliveryservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.deliveryservice.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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


    public List<OrderDto> getAllOrdersDeliveries() {
        List<Order> orders =  new ArrayList<>();
        deliveryRepository.findAll().forEach(orders::add);
        List<OrderDto> orderDtos = orders.stream().map(this::mapToOrderDto).toList();
        for(OrderDto orderDto : orderDtos){
            String address = getAddress(orderDto.getId());
            orderDto.setAddress(address);
        }
        return orderDtos;
    }

    private OrderDto mapToOrderDto(Order order) {
        OrderDto orderdto = OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .pizzaCode(order.getPizzaCode())
                .pizzaQuantity(order.getPizzaQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
        return orderdto;
    }
    private String getAddress(String id) {
        String address = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8083/api/orders/address/{id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return address;
    }
}

