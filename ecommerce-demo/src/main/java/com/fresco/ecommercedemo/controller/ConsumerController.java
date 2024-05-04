package com.fresco.ecommercedemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.ecommercedemo.models.Cart;
import com.fresco.ecommercedemo.models.CartProduct;
import com.fresco.ecommercedemo.models.Product;
import com.fresco.ecommercedemo.service.ConsumerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/consumer")
@RequiredArgsConstructor
public class ConsumerController {
	private final ConsumerService consumerService;

	@GetMapping("/cart")
	public ResponseEntity<Cart> getCart() {
		return consumerService.getCart(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@PostMapping("/cart")
	public ResponseEntity<Object> postCart(@RequestBody Product product) {
		return consumerService.addProductToCart(product,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@PutMapping("/cart")
	public ResponseEntity<Object> putCart(@RequestBody CartProduct cartProduct) {
		return consumerService.updateCartProduct(cartProduct,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@DeleteMapping("/cart")
	public ResponseEntity<Object> deleteCart(@RequestBody Product product) {
		return consumerService.deleteCartProduct(product,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
