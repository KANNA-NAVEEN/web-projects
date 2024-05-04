package com.somnath.complaintapp.exceptions;

public class AlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String message) {
		super(message);
	}
}