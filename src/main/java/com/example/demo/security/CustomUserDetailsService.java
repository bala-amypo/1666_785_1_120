package com.example.demo.security;

public class JwtUtil {
    public String generateToken(java.util.Map<String, Object> claims, String subject) { return null; }
    public String getUsername(String token) { return null; }
    public boolean isTokenValid(String token, String username) { return false; }
}

public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) { return null; }
}