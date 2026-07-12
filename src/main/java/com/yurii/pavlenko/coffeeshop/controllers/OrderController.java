package com.yurii.pavlenko.coffeeshop.controllers;

import com.yurii.pavlenko.coffeeshop.config.CoffeeShopProperties;
import com.yurii.pavlenko.coffeeshop.models.*;
import com.yurii.pavlenko.coffeeshop.validators.OrderValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OrderController {

    private final CoffeeShopProperties coffeeShopProperties;
    private final OrderValidator orderValidator;
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<OrderResponseDTO> ordersRepository = new ArrayList<>();

    public OrderController(CoffeeShopProperties coffeeShopProperties, OrderValidator orderValidator) {
        this.coffeeShopProperties = coffeeShopProperties;
        this.orderValidator = orderValidator;
    }

    @GetMapping("/shop-name")
    public String shopName() {
        return coffeeShopProperties.name();
    }

    @GetMapping("/max-orders")
    public int maxOrders() {
        return coffeeShopProperties.maxOrders();
    }

    @GetMapping("/info")
    public String info() {
        return "Coffee shop " + coffeeShopProperties.name() + " - " + coffeeShopProperties.city() + " - max orders: " + coffeeShopProperties.maxOrders();
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDTO> addOrder(@RequestBody OrderRequestDTO request) {

        orderValidator.validate(request);

        Order order = new Order(
                idGenerator.getAndIncrement(),
                request.item(),
                request.quantity());

        OrderResponseDTO response = new OrderResponseDTO(
                order.id(),
                order.item(),
                order.quantity(),
                coffeeShopProperties.name());

        ordersRepository.add(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/orders")
    public List<OrderResponseDTO> getAllOrders() {
        return ordersRepository;
    }
}