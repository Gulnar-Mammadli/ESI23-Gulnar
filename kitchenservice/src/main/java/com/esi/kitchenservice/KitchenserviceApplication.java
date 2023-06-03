package com.esi.kitchenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KitchenserviceApplication {

	public static void main(String[] args) {
		DatabaseInitializer.initialize("kitchenservice_db");
		SpringApplication.run(KitchenserviceApplication.class, args);
	}
}
