package com.esi.deliveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DeliveryserviceApplication {


	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return  WebClient.builder();
	}


	public static void main(String[] args) {
		DatabaseInitializer.initialize("deliverypizzaservice_db");
		SpringApplication.run(DeliveryserviceApplication.class, args);
	}
}
