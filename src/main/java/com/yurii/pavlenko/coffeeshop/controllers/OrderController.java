package com.yurii.pavlenko.coffeeshop.controllers;

import com.yurii.pavlenko.coffeeshop.CoffeeShopProperties;
import com.yurii.pavlenko.coffeeshop.models.Order;
import com.yurii.pavlenko.coffeeshop.models.OrderRequestDTO;
import com.yurii.pavlenko.coffeeshop.models.OrderResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OrderController {

    private final CoffeeShopProperties coffeeShopProperties;
    private final AtomicLong idGenerator = new AtomicLong(1);

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

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDTO> addOrder(@RequestBody OrderRequestDTO request) {

        Order order = new Order(
                idGenerator.getAndIncrement(),
                request.getItem(),
                request.getQuantity());

        OrderResponseDTO response = new OrderResponseDTO(
                order.getId(),
                order.getItem(),
                order.getQuantity(),
                coffeeShopProperties.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}