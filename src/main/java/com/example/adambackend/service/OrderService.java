package com.example.adambackend.service;

import com.example.adambackend.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();

    Order create(Order order);

    void deleteById(Long id);

    Optional<Order> findById(Long id);
}
