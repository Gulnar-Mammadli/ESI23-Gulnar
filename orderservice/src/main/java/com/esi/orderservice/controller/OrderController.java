package com.esi.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esi.orderservice.dto.OrderDto;
import com.esi.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String>  createOrder(@RequestBody OrderDto orderDto) {
    orderService.addOrder(orderDto);
    return ResponseEntity.ok("Order recieved");
    }

    @GetMapping("/orders/address/{id}")
    public String getOrderAddress(@PathVariable String id){
        return orderService.getAddressById(id);
    }

}
