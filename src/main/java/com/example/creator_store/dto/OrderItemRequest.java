package com.example.creator_store.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull(message = "Quantity ID is required")
    @Min(value = 1,message = "Quantity must be greater than 0")
    private Integer quantity;


    @NotNull(message = "Product ID is required")
    private Long productId;
}
