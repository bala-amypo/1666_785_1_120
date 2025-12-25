package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationFilter {

    private JwtUtil jwtUtil;

    public void doFilter(String token, UserDetails userDetails) {

        String username = jwtUtil.getUsername(token);

        if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
            // valid
        }
    }
}
