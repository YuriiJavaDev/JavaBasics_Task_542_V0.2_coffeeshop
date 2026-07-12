package com.yurii.pavlenko.coffeeshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequestDTO(
        @JsonProperty("item") String item,
        @JsonProperty("quantity") int quantity
) {}