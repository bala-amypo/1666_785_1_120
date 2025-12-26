package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository keyRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo,
                                           ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new BadRequestException("Key missing"));

        if (e.getLimitExceededBy() <= 0) {
            throw new BadRequestException("Invalid exceed value");
        }

        return repo.save(e);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long id) {
        return repo.findByApiKey_Id(id);
    }
}
