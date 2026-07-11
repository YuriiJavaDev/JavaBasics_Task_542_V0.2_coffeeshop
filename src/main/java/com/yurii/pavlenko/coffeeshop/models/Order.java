package com.yurii.pavlenko.coffeeshop.models;

public class Order {
    private final long id;
    private final String item;
    private final int quantity;

    public Order(long id, String item, int quantity) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
    }

    public long getId() { return id; }
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
}
