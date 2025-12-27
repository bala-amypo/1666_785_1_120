package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ApiUsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpoint;

    private Instant timestamp;

    @ManyToOne
    private ApiKey apiKey;

    // getters & setters
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Long getId() {
        return id;
    }
}
