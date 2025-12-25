package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import java.util.HashMap;

public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {
        String token = jwtUtil.generateToken(new HashMap<>(), request.getEmail());
        return new AuthResponseDto(token);
    }
}
