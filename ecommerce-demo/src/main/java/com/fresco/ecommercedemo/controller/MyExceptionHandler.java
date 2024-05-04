package com.fresco.ecommercedemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> handleParameterMissing(MissingServletRequestParameterException exception){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
