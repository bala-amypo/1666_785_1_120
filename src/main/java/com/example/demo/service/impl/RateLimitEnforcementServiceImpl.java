package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

@Service
public class RateLimitEnforcementServiceImpl
        implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository) {
        this.repository = repository;
    }

    @Override
    public RateLimitEnforcement save(RateLimitEnforcement enforcement) {
        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }
}
