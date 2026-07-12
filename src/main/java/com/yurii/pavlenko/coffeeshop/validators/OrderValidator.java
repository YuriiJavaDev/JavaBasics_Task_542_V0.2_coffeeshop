package com.yurii.pavlenko.coffeeshop.validators;

import com.yurii.pavlenko.coffeeshop.exceptions.ValidationException;
import com.yurii.pavlenko.coffeeshop.models.OrderRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    public void validate(OrderRequestDTO request) {
        if (request.item() == null || request.item().isBlank()) {
            throw new ValidationException("The item field cannot be empty.");
        }
        if (request.quantity() <= 0) {
            throw new ValidationException("The quantity must be greater than zero.");
        }
    }
}