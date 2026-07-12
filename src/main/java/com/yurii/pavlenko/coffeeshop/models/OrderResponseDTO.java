package com.yurii.pavlenko.coffeeshop.models;

public record OrderResponseDTO(long id, String item, int quantity, String shopName) {

}
