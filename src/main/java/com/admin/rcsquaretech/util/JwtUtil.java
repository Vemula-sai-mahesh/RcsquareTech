package com.admin.rcsquaretech.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
    public static long TOKEN_VALIDITY = 1000L * 60 * 60 * 3; //3 hours
    
    // Generate JWT Token
    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        long expiryTime = now + TOKEN_VALIDITY;
        System.out.println("Current Time: " + now);
        System.out.println("Expiration Time: " + expiryTime);
        System.out.println("Expiration Date: " + new Date(expiryTime));

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiryTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extract Username from JWT Token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
