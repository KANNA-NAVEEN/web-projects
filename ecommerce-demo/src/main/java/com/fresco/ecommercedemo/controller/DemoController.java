package com.fresco.ecommercedemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/secure")
public class DemoController {
	@GetMapping(value = "demo")
	public ResponseEntity<String> secured(){
		return ResponseEntity.ok("This URI is secured");
	}
}
