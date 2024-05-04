package com.fresco.ecommercedemo.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.ecommercedemo.models.Product;
import com.fresco.ecommercedemo.service.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/seller")
@RequiredArgsConstructor
public class SellerController {
	private final SellerService sellerService;

	@PostMapping("/product")
	public ResponseEntity<Object> postProduct(@RequestBody Product product) throws URISyntaxException {
		return sellerService.addProduct(product, SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProduct() {
		return sellerService.getAllProducts(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Integer productId) {
		return sellerService.getProduct(productId, SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@PutMapping("/product")
	public ResponseEntity<Object> putProduct(@RequestBody Product product) {
		return sellerService.updateProduct(product, SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(name = "productId") Integer productId) {
		return sellerService.deleteProduct(productId, SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
