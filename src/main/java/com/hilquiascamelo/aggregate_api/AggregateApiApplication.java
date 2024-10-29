package com.hilquiascamelo.aggregate_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hilquiascamelo.aggregate_api")
public class AggregateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregateApiApplication.class, args);
	}

}
