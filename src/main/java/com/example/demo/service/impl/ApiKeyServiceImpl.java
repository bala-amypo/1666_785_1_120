package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import java.util.List;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final QuotaPlanRepository quotaPlanRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository quotaPlanRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.quotaPlanRepo = quotaPlanRepo;
    }

    @Override
    public ApiKey createApiKey(ApiKey key) {
        QuotaPlan plan = quotaPlanRepo.findById(key.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (!plan.isActive()) {
            throw new BadRequestException("Plan inactive");
        }

        key.setPlan(plan);
        return apiKeyRepo.save(key);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
    }

    @Override
    public ApiKey getApiKeyByValue(String value) {
        return apiKeyRepo.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey key) {   // ✅ REQUIRED BY CONTROLLER
        ApiKey existing = getApiKeyById(id);

        if (key.getPlan() != null) {
            existing.setPlan(key.getPlan());
        }
        existing.setActive(key.isActive());
        existing.setOwnerId(key.getOwnerId());

        return apiKeyRepo.save(existing);
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
        apiKeyRepo.save(key);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }
}
