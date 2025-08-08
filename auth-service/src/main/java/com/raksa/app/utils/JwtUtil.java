package com.raksa.app.utils;

import com.raksa.app.dtos.responses.UserResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;

public class JwtUtil {
    private static final Key key = Keys.hmacShaKeyFor("changeMeChangeMeChangeMeChangeMe123".getBytes());

    /*
        * Generates a JWT token for the given username.
        * @param username the username to include in the token
        * @return a JWT token as a String
     */
    public static String generateToken(UserResponseDto userResponseDto) {
        return Jwts.builder()
                .claim("id", userResponseDto.getId())
                .setSubject(userResponseDto.getUsername())
                .claim("password", userResponseDto.getPassword())
                .claim("role", userResponseDto.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1-hour expiration
                .signWith(key)
                .compact();
    }

    /*
        * Validates the JWT token and returns the username if valid.
        * @param token the JWT token
        * @return the username contained in the token
        * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
     */
    public static String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /*
        * Extracts claims from the JWT token.
        * @param token the JWT token
        * @return the claims contained in the token
        * @throws io.jsonwebtoken.JwtException if the token is invalid or expired
     */
    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}