package com.yurii.pavlenko.coffeeshop.models;

public class OrderRequestDTO {

    private final String item;
    private final int quantity;

    public OrderRequestDTO(long id, String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }


    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
