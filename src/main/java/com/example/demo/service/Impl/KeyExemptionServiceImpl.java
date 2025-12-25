package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.KeyExemptionService;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public KeyExemption createExemption(KeyExemption ex) {
        if (ex.getTemporaryExtensionLimit() < 0)
            throw new BadRequestException("Invalid");

        keyRepo.findById(ex.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        return repo.save(ex);
    }

    public KeyExemption getExemptionByKey(Long keyId) {
        return repo.findByApiKey_Id(keyId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
}
