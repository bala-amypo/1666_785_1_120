package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ApiKey apiKey;

    private int limitExceededBy;

    private String message;

    // getters & setters
    public int getLimitExceededBy() {
        return limitExceededBy;
    }

    public void setLimitExceededBy(int limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
