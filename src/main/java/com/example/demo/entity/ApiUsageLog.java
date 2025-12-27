package com.example.demo.entity;

import java.time.Instant;
import jakarta.persistence.*;

@Entity
public class ApiUsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ApiKey apiKey;

    private String endpoint;

    // ✅ MUST be Instant (tests use Instant)
    private Instant timestamp;

    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
