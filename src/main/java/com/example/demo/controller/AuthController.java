package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;

public class AuthController {

    private AuthService authService;

    public void register(RegisterRequestDto dto) {
        authService.register(dto);
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        return authService.login(dto);
    }
}
