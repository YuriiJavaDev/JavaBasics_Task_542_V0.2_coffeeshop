## Coffeeshop Configuration Service (JavaBasics_Task_542_V0.2)

### 📖 Description
This project implements a robust order processing service for a coffee shop. It integrates REST API development with strict input validation, centralized error handling using @ControllerAdvice, and type-safe configuration management via @ConfigurationProperties.

### 📋 Requirements Compliance
- **Validation**: Implemented `OrderValidator` to ensure data integrity (non-empty items, positive quantities).
- **Global Error Handling**: Used `@ControllerAdvice` to provide consistent `ErrorResponse` for business and parsing exceptions.
- **REST API**: Implemented POST /orders with proper status codes (201 Created, 400 Bad Request).
- **Configuration**: Used @ConfigurationProperties for managing shop settings across different profiles.

### 🚀 Architectural Stack
- **Framework**: Spring Boot 3.x
- **Language**: Java 23
- **Configuration**: YAML with Spring Profiles
- **Design Patterns**: Controller-Validator-Service structure

### 🏗️ Implementation Details
- **Error Handling**: `ApiExceptionHandler` catches `ValidationException` and `HttpMessageNotReadableException` to return uniform JSON errors.
- **Validation**: Business logic separated into `OrderValidator` using Dependency Injection.
- **Data Transfer**: Used DTOs (Request/Response) with standard Jackson serialization.
- **Properties**: `CoffeeShopProperties` bound to `coffee.shop` prefix.

### 🎯 Expected result
- Application properly validates user input and returns 400 status on invalid data.
- Successful orders are stored in memory and return 201 status with populated shop information.
- Consistent API error responses across all types of failure.

### Project Structure:

    JavaBasics_Task_542_V0.2_coffeeshop/
    ├─ src/main/
    │      ├────────────── java/com/yurii/pavlenko/coffeeshop/
    │      │                                       ├─ app/
    │      │                                       │  └─ CoffeeshopApplication.java
    │      │                                       ├─ config/
    │      │                                       │  └─ CoffeeShopProperties.java
    │      │                                       ├─ controllers/
    │      │                                       │  └─ OrderController.java
    │      │                                       ├─ exceptions/
    │      │                                       │  ├─ ApiExceptionHandler.java
    │      │                                       │  └─ ValidationException.java
    │      │                                       ├─ models/
    │      │                                       │  ├─ ErrorResponse.java
    │      │                                       │  ├─ Order.java
    │      │                                       │  ├─ OrderRequestDTO.java
    │      │                                       │  └─ OrderResponseDTO.java
    │      │                                       └─ validators/
    │      │                                          └─ OrderValidator.java
    │      └─ resources/                              
    │         ├─ application.yaml                  
    │         ├─ application-dev.yaml              
    │         └─ application-prod.yaml
    ├── pom.xml
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

## 💻 Code Example

```java
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

```

```java
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
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
