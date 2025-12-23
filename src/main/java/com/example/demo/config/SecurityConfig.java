// package com.example.demo.config;

// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class SecurityConfig {
//     public void passwordEncoder() {}
//     public void configureHttp() {}
// }
// In your SecurityConfig.java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
            .anyRequest().authenticated()
        )
        .build();
}