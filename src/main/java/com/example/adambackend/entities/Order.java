package com.example.adambackend.entities;

import com.example.adambackend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @Column(name="address_detail")
    private String addressDetail;
    @Column(name="time_create")
    private LocalDateTime timeCreate;
    private OrderStatus status;
    private String fullName;
    private String phoneNumber;
}