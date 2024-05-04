package com.fresco.ecommercedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final ApiAuthenticationEntryPoint apiAuthenticationEntryPoint;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> request
					.requestMatchers("/api/public/**").permitAll()
					.requestMatchers("/api/auth/consumer/**").hasAnyAuthority("CONSUMER")
					.requestMatchers("/api/auth/seller/**").hasAnyAuthority("SELLER")
					.anyRequest().authenticated())
			.exceptionHandling(customizer -> customizer.authenticationEntryPoint(apiAuthenticationEntryPoint))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}

//	@Bean
//	JwtAuthenticationFilter jwtAuthFiIterRegister(JwtAuthenticationFilter fitter) {
//		return fitter;
//	}

	
}
