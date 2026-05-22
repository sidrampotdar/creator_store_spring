package com.example.creator_store.services;

import com.example.creator_store.dto.OrderItemRequest;
import com.example.creator_store.dto.OrderRequest;
import com.example.creator_store.entities.Order;
import com.example.creator_store.entities.OrderItem;
import com.example.creator_store.entities.Product;
import com.example.creator_store.repositories.OrderRepository;
import com.example.creator_store.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        Order order = new Order();

        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for (OrderItemRequest itemRequest : orderRequest.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product Not Found with id "
                                            + itemRequest.getProductId()));

            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException(
                        "Not enough stock for product with id "
                                + itemRequest.getProductId());
            }

            BigDecimal priceOfItem =
                    product.getPrice()
                            .multiply(new BigDecimal(itemRequest.getQuantity()));

            totalPrice = totalPrice.add(priceOfItem);

            product.setStockQuantity(
                    product.getStockQuantity() - itemRequest.getQuantity());

            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();

            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItemsList(orderItems);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderByid(long id) {
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order Not Found with id " + id));
    }
}