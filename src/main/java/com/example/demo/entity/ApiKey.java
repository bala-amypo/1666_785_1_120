package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_keys")
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String keyValue;

    private Long ownerId;

    @ManyToOne
    private QuotaPlan plan;

    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ApiKey() {}

    public ApiKey(String keyValue, Long ownerId, QuotaPlan plan, Boolean active) {
        this.keyValue = keyValue;
        this.ownerId = ownerId;
        this.plan = plan;
        this.active = active != null ? active : true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getKeyValue() { return keyValue; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public QuotaPlan getPlan() { return plan; }
    public void setPlan(QuotaPlan plan) { this.plan = plan; }
}