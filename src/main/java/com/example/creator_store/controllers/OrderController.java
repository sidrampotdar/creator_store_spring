package com.example.creator_store.controllers;

import com.example.creator_store.dto.OrderRequest;
import com.example.creator_store.repositories.OrderRepository;
import com.example.creator_store.services.OrderService;
import com.example.creator_store.entities.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@Valid  @RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
    @GetMapping("/")
    public List<Order> getAllOrders(){
            return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id) {
       return  orderService.getOrderByid(id);
    }
}
