## Coffeeshop Configuration Service (JavaBasics_Task_542_V0.1)
### 📖 Description
This project demonstrates advanced configuration management in Spring Boot using @ConfigurationProperties. It shows how to map external YAML properties to strongly-typed Java objects and how to leverage environment-specific profiles (dev and prod) to manage varying business constraints.

### 📋 Requirements Compliance
Configuration Mapping: Used @ConfigurationProperties for type-safe property binding.

Auto-scan: Implemented @ConfigurationPropertiesScan for automatic discovery of configuration beans.

Environment Profiles: Configured multi-profile YAML files (application-dev.yaml, application-prod.yaml) to switch between testing and production parameters.

REST Integration: Created OrderController to expose business information based on injected configuration.

### 🚀 Architectural Stack
Framework: Spring Boot 3.x

Language: Java 23

Configuration Format: YAML

### 🏗️ Implementation Details
CoffeeShopProperties: A POJO mapped to coffee.shop prefix in YAML, ensuring type-safe access to application settings.

YAML Hierarchy: Used a base application.yaml for shared settings and profile-specific files for overriding business logic (shop name and order limits).

OrderController: Demonstrates dependency injection of configuration beans into web layer components.

### 🎯 Expected result
Application runs on port 8081.

API endpoints return data dynamically based on the active profile (-Dspring.profiles.active=dev or prod).

Successful binding of nested YAML structures to Java fields.

### Project Structure:

    JavaBasics_Task_542/
    ├─ src/main/
    │      ├────────────── java/com/yurii/pavlenko/coffeeshop/
    │      └─ resources/                           ├── CoffeeshopApplication.java
    │         ├─ application.yaml                  ├── CoffeeShopProperties.java
    │         ├─ application-dev.yaml              └── OrderController.java
    │         └─ application-prod.yaml
    ├── pom.xml
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

## 💻 Code Example

```java
package com.yurii.pavlenko.coffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CoffeeshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeshopApplication.class, args);
    }
}
```
```java
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
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
