package com.somnath.complaintapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.somnath.complaintapp.exceptions.AlreadyExistsException;
import com.somnath.complaintapp.exceptions.InvalidIdException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<String> handleInvalidCredentialsException(InvalidIdException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}
}
