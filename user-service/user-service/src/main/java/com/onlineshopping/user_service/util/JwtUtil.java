package com.onlineshopping.user_service.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate JWT Token
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key)
                .compact();

    }

    // Extract Email
    public String extractEmail(String token) {

        return extractClaims(token).getSubject();

    }

    // Extract Role
    public String extractRole(String token) {

        return extractClaims(token).get("role", String.class);

    }

    // Validate Token
    public boolean validateToken(String token, String email) {

        return extractEmail(token).equals(email)
                && !isTokenExpired(token);

    }

    // Check Expiration
    private boolean isTokenExpired(String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());

    }

    // Extract Claims
    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

}