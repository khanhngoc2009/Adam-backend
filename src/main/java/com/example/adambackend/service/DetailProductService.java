package com.example.adambackend.service;

import java.util.List;
import java.util.Optional;

import com.example.adambackend.entities.DetailProduct;

public interface DetailProductService {

	Optional<DetailProduct> findById(Long id);

	void deleteById(Long id);

	DetailProduct create(DetailProduct DetailProduct);

	List<DetailProduct> findAll();
}
