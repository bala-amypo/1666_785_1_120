package com.example.demo.security;

import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {} [cite: 447]
}