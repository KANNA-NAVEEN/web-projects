package com.fresco.ecommercedemo.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//This implementation is for handling unauthorized access. This method will be invoked once any unauthorized request is sent
@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("You are not authorized to access this resource");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this resource");
	}

}
