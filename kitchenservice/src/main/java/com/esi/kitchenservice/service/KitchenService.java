package com.esi.kitchenservice.service;

import com.esi.kitchenservice.dto.OrderStatus;
import com.esi.kitchenservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.esi.kitchenservice.dto.OrderDto;
import com.esi.kitchenservice.repository.KitchenRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    private final KafkaTemplate<String, OrderDto> kafkaDeliveryTemplate;

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
        Optional<Order> response = kitchenRepository.findById(orderDto.getId());
        if(response.isPresent()){
            Order order = response.get();
            order.setId(orderDto.getId());
            order.setUserId(orderDto.getUserId());
            order.setPizzaCode(orderDto.getPizzaCode());
            order.setPizzaQuantity(orderDto.getPizzaQuantity());
            order.setOrderStatus(OrderStatus.Ready);

            kitchenRepository.save(order);
            orderDto.setOrderStatus(OrderStatus.Ready);
            kafkaDeliveryTemplate.send("orderReadyTopic",orderDto);
            log.info("A order request id: {} has been updated and sent to delivery service", orderDto.getId());
        }
    }
}

