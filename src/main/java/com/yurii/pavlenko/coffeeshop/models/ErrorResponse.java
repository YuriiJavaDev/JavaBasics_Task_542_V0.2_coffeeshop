package com.yurii.pavlenko.coffeeshop.models;

@SuppressWarnings("unused")
public record ErrorResponse(int status, String message) {
}