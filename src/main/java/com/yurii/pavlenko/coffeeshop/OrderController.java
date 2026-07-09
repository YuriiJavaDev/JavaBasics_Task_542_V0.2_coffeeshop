package com.yurii.pavlenko.coffeeshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final CoffeeShopProperties coffeeShopProperties;

    public OrderController(CoffeeShopProperties coffeeShopProperties){
        this.coffeeShopProperties = coffeeShopProperties;
    }

    @GetMapping("/shop-name")
    public String shopName() {
        return coffeeShopProperties.getName();
    }

    @GetMapping("/max-orders")
    public int maxOrders() {
        return coffeeShopProperties.getMaxOrders();
    }

    @GetMapping("/info")
    public String info() {
        return "Coffee shop " + coffeeShopProperties.getName() + " - " + coffeeShopProperties.getCity() + " - max orders: " + coffeeShopProperties.getMaxOrders();
    }
}