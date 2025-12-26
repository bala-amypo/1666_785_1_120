package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImpl {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository repo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequestDto dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new BadRequestException("Email exists");
        }

        UserAccount u = new UserAccount();
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setRole(dto.getRole());
        repo.save(u);
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        UserAccount user = repo.findByEmail(dto.getEmail())
                .orElseThrow(RuntimeException::new);

        String token = jwtUtil.generateToken(null, user.getEmail());
        return new AuthResponseDto(token);
    }
}
