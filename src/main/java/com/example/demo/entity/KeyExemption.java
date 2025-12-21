package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 104]

    @ManyToOne
    private ApiKey apiKey; [cite: 105]

    private String notes; [cite: 106]
    private Boolean unlimitedAccess; [cite: 107]
    private Integer temporaryExtensionLimit; [cite: 108]
    private LocalDateTime validUntil; [cite: 109]

    public KeyExemption() {} [cite: 111]

    public KeyExemption(ApiKey apiKey, String notes, Boolean unlimitedAccess, Integer temporaryExtensionLimit, LocalDateTime validUntil) {
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil = validUntil;
    } [cite: 112]

    // Getters and Setters
    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public void setTemporaryExtensionLimit(Integer limit) { this.temporaryExtensionLimit = limit; }
    public LocalDateTime getValidUntil() { return validUntil; }
    public void setValidUntil(LocalDateTime validUntil) { this.validUntil = validUntil; }
}