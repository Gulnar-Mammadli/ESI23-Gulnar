package com.esi.deliveryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.deliveryservice.dto.OrderDto;
import com.esi.deliveryservice.service.DeliveryService;


@RestController
@RequestMapping("/api")
public class DeliveryController {

@Autowired
private DeliveryService deliveryService;

/* 
@GetMapping("/delivery")
public List<OrderDto> getAllOrdersDeliveries(){
return deliveryService.getAllOrdersDeliveries();
} 
*/

}
