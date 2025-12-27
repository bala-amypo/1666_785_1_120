package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ApiKey apiKey;

    private boolean blocked;

    // ✅ REQUIRED BY TESTS
    private int limitExceededBy;

    // ✅ REQUIRED BY TESTS
    private String message;

    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    // ✅ TEST METHODS
    public int getLimitExceededBy() {
        return limitExceededBy;
    }

    public void setLimitExceededBy(int limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    // ✅ TEST METHODS
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
