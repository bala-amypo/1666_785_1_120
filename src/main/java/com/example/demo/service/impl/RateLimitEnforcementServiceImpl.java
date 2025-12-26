package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo) {
        this.repo = repo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        return repo.save(e);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long id) {
        return repo.findAllByApiKey_Id(id); 
    }
}
