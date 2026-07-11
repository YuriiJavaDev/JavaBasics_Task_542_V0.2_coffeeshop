package com.yurii.pavlenko.coffeeshop.models;

public class OrderResponseDTO {

    private final long id;
    private final String item;
    private final int quantity;
    private final String shopName;

    public OrderResponseDTO(long id, String item, int quantity, String shopName) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.shopName = shopName;
    }

    public long getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShopName() {
        return shopName;
    }
}
