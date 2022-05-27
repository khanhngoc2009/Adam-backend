package com.example.adambackend.service;

import com.example.adambackend.entities.Ward;

import java.util.List;
import java.util.Optional;

public interface WardService {
    List<Ward> findAll();

    Ward save(Ward ward);

    void deleteById(Long id);

    Optional<Ward> findById(Long id);
}
