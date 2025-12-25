package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import java.time.Instant;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo,
                                   ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption ex) {
        ApiKey key = apiKeyRepo.findById(ex.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));

        if (ex.getTemporaryExtensionLimit() <= 0) {
            throw new BadRequestException("Invalid limit");
        }

        if (ex.getValidUntil() != null && ex.getValidUntil().isBefore(Instant.now())) {
            throw new BadRequestException("Invalid expiry");
        }

        ex.setApiKey(key);
        return repo.save(ex);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));
    }

    @Override
    public void deleteExemption(Long apiKeyId) {
        KeyExemption ex = getExemptionByKey(apiKeyId);
        repo.delete(ex);
    }

    @Override
    public Iterable<KeyExemption> getAllExemptions() {
        return repo.findAll();
    }
}
