package com.example.demo.config;


import org.springframework.context.annotation.Bean; [cite: 332]
import org.springframework.context.annotation.Configuration; [cite: 332]
import org.springframework.security.config.annotation.web.builders.HttpSecurity; [cite: 333]
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; [cite: 332]
import org.springframework.security.web.SecurityFilterChain; [cite: 333]

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public Object filterChain(Object http) { return null; }
    public Object passwordEncoder() { return null; }
}