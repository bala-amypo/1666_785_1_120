package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo,
                                   ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption e) {
        keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new BadRequestException("Key missing"));

        if (e.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid limit");
        }

        return repo.save(e);
    }

    @Override
    public KeyExemption getExemptionByKey(Long id) {
        return repo.findByApiKey_Id(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
}
