package com.fresco.ecommercedemo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.ecommercedemo.dto.AuthenticationRequest;
import com.fresco.ecommercedemo.models.Product;
import com.fresco.ecommercedemo.service.AuthenticationService;
import com.fresco.ecommercedemo.service.PublicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {
	private final AuthenticationService authService;
	private final PublicService publicService;

	@GetMapping("/product/search")
	public List<Product> getProducts(@RequestParam(required = true, value = "keyword") String keyword) {
		return publicService.getProducts(keyword);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthenticationRequest request) {
		return authService.authenticate(request);
	}
}
