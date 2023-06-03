package com.esi.kitchenservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.kitchenservice.dto.OrderDto;
import com.esi.kitchenservice.service.KitchenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;


    @PutMapping("/orders")
    public ResponseEntity<String> orderReady(@RequestBody OrderDto orderDto) {
        kitchenService.orderReady(orderDto);
    return ResponseEntity.ok("An order status has update  to ready");

    }
}
