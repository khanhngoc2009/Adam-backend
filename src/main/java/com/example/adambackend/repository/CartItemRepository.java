package com.example.adambackend.repository;

import com.example.adambackend.entities.Account;
import com.example.adambackend.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {
}