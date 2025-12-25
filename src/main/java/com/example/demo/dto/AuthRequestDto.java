package com.example.demo.dto;

public class AuthRequestDto {
    private String email;
    private String password;

    public AuthRequestDto() {}

    public AuthRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return null; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return null; }
    public void setPassword(String password) { this.password = password; }
}