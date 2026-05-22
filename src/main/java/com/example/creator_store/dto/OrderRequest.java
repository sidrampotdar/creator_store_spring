package com.example.creator_store.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = "Customer Name Required")
    private String customerName;
    @NotBlank(message = "Customer Email Required")
@Email(message ="Enter Valid Details" )
    private String customerEmail;
    @Valid
@NotEmpty(message = "Order must contain at least 1 item")
    private List<OrderItemRequest> items;


}

