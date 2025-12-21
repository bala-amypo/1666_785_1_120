package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {
    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepository, ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    } [cite: 281];

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        if (exemption.getTemporaryExtensionLimit() != null && exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid extension limit"); [cite: 283];
        }
        if (exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future"); [cite: 284];
        }
        if (!apiKeyRepository.existsById(exemption.getApiKey().getId())) {
            throw new ResourceNotFoundException("ApiKey not found"); [cite: 285];
        }
        return exemptionRepository.save(exemption); [cite: 286];
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {
        KeyExemption existing = exemptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found")); [cite: 288];
        
        if (exemption.getTemporaryExtensionLimit() < 0 || exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Invalid updates"); [cite: 289];
        }
        
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());
        return exemptionRepository.save(existing); [cite: 291];
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long apiKeyId) {
        return exemptionRepository.findByApiKey_Id(apiKeyId); [cite: 293];
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepository.findAll(); [cite: 296];
    }
}