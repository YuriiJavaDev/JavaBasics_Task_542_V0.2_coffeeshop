package com.yurii.pavlenko.coffeeshop;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coffee.shop")
public class CoffeeShopProperties {

        private final String name;
        private final String city;
        private final int maxOrders;

        public CoffeeShopProperties(String name, String city, int maxOrders) {
            this.name = name;
            this.city = city;
            this.maxOrders = maxOrders;
        }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getMaxOrders() {
        return maxOrders;
    }
}
