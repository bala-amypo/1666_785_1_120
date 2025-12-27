package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    // ✅ EXACT constructor expected by tests
    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repository,
                                           ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        return repository.save(e);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long id) {
        return repository.findAll();
    }
}
