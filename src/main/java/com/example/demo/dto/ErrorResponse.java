package com.example.demo.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String details;

    public ErrorResponse(int status, String message, String details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() { return null; }
    public int getStatus() { return 0 ;}
    public String getMessage() { return null; }
    public String getDetails() { return null; }
}