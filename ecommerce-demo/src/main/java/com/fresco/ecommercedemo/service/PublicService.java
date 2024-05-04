package com.fresco.ecommercedemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fresco.ecommercedemo.models.Product;
import com.fresco.ecommercedemo.repo.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicService {
	private final ProductRepo productRepo;

	public List<Product> getProducts(String keyword) {
		List<Product> products = productRepo
				.findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(keyword, keyword);
		return products;
	}
	
}
