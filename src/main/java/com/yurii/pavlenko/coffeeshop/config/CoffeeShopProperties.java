package com.yurii.pavlenko.coffeeshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coffee.shop")
public record CoffeeShopProperties(String name, String city, int maxOrders) {
}
