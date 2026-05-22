package com.example.creator_store.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
    @Column(name="customer_name",nullable = false)
 private  String customerName;
    @Column(name="customer_email",nullable = false)
 private  String customerEmail;
    @Column(nullable = false,name = "total_price")
 private BigDecimal totalPrice;
    @Column(nullable = false)
private String status;
    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItemsList;
    @Column(name = "created_at")
private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt=LocalDateTime.now();
    }

}
