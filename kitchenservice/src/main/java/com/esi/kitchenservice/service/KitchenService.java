package com.esi.kitchenservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esi.kitchenservice.dto.OrderDto;
import com.esi.kitchenservice.repository.KitchenRepository;



@Service
public class KitchenService {

@Autowired
private KitchenRepository kitchenRepository;



public void orderReady(OrderDto orderDto) {

}
}

