package com.example.creator_store.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Product Must have a name")
    private String name;

    private String description;
    private String category;

    @NotNull(message = "Price is required")
    @Column(nullable = false)
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Price Must Be Greater Than Zero")
    private BigDecimal price;
    @NotNull(message = "Stock must be positive value")
    @Min(value = 0,message = "Stock Can not be Negative")
    @Column(nullable = false,name = "stock_quantity")
    private Integer stockQuantity;

    @OneToMany(mappedBy = "product")
    @JsonIgnore // To Ignore Seq of products
    private List<OrderItem> orderItemList;

}
