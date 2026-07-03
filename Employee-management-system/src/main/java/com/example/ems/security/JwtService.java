package com.example.ems.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey";//key is used to sign and verify JWT.

	//method creates JWT after successful login
	public String generateToken(String username) {
               //Stores username inside token   created time.
		return Jwts.builder().subject(username).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).signWith(getSigningKey()).compact();
	            //expiry time.                                                 Signs token using secret key | final token string
	}
	
	//Convert secret key string into signing key object -> Token generation/validation
	private Key getSigningKey() {

		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	//Reads username from JWT.
	public String extractUsername(String token) {

		return extractAllClaims(token).getSubject();
	}

	//Check whether token is valid for this user.
	public boolean validateToken(String token, String username) {

		String tokenUsername = extractUsername(token);

		return tokenUsername.equals(username) && !isTokenExpired(token);
	}

	//Check whether token expiry time is already passed.
	private boolean isTokenExpired(String token) {

		return extractAllClaims(token).getExpiration().before(new Date());
	}

	//Decode and verify JWT token.generate/validate both 
	private Claims extractAllClaims(String token) {

		return Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey()).build().parseSignedClaims(token)
				.getPayload();
	}
	
	/*
	 * extractUsername() validateToken() isTokenExpired() extractAllClaims()
	 * getSigningKey()
	 */

}
