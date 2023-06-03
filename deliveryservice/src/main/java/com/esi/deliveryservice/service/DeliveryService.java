package com.esi.deliveryservice.service;


import org.springframework.beans.factory.annotation.Autowired;
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


}

