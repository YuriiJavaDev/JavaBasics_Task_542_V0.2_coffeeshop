package com.yurii.pavlenko.coffeeshop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.yurii.pavlenko.coffeeshop")
@ConfigurationPropertiesScan("com.yurii.pavlenko.coffeeshop")
public class CoffeeshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeshopApplication.class, args);
	}
}
