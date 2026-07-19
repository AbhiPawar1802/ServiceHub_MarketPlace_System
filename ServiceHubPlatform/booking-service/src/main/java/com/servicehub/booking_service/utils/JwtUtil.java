package com.servicehub.booking_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "servicehub-auth-secret-key-256-bit-long!!";
    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Long extractUserId(String token){
        Claims claims = extractClaims(token);
        Object authUserId = claims.get("authUserId");

        if(authUserId == null){
            throw new RuntimeException("authUserId not found in the token");
        }
        return Long.valueOf(authUserId.toString());
    }
    public String extractRole(String token){
        Claims claims = extractClaims(token);
        return claims.get("role").toString();
    }
}
