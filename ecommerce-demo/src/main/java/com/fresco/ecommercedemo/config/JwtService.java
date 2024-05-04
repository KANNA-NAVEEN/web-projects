package com.fresco.ecommercedemo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
	// https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
	private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B597P";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username) {
		return generateToken(new HashMap<>(), username);
	}

	public String generateToken(Map<String, Object> extraClaims, String username) {
		return Jwts.builder().setClaims(extraClaims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+900000))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(HttpServletRequest request, String token, UserDetails userDetails) {
		final String username = extractUsername(token);
//		final String URI=request.getRequestURI();
//		if(URI.contains("seller") && !((User) userDetails).isSeller()){
//			throw new AccessDeniedException("You have no access");
//		}
//		if(URI.contains("consumer") && !((User) userDetails).isConsumer()){
//			throw new AccessDeniedException("You have no access");
//		}
		return userDetails.getUsername().equals(username) && !isTokenExpired(token);
	}

	

	

	private boolean isTokenExpired(String token) {
		return extractExpirationDate(token).before(new Date());
	}

	private Date extractExpirationDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
