package com.example.demo.security;

import io.jsonwebtoken.Claims;

public class JwtUtil {

    public String generateToken(java.util.Map<String, Object> claims, String username) {
        return "DUMMY_TOKEN";
    }

    public Claims getClaims(String token) {
        return null;
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(getUsername(token));
    }

    public long getExpirationMillis() {
        return 1000 * 60 * 60;
    }
}
