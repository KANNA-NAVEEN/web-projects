package com.fresco.ecommercedemo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final UserDetailsService userAuthService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		String authHeader = request.getHeader("Authorization");
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		System.out.println("Bearer found");
//		String jwt = authHeader.substring(7);
		String authHeader = request.getHeader("JWT");
		if (authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwt = authHeader.substring(0,authHeader.length());
		String username = jwtService.extractUsername(jwt);
		if (username == null || SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userAuthService.loadUserByUsername(username);
			if (jwtService.isTokenValid(request,jwt, userDetails)) {
				System.out.println("Validated succesfully");
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				request.setAttribute("username",username);
			}
		}
		filterChain.doFilter(request, response);
	}

}
