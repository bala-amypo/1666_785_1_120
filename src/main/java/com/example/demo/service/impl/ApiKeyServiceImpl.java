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
        return apiKeyRepo.save(key);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey key) {
        ApiKey existing = getApiKeyById(id);
        existing.setActive(key.isActive());
        existing.setOwnerId(key.getOwnerId());
        existing.setPlan(key.getPlan());
        return apiKeyRepo.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
    }

    @Override
    public ApiKey getApiKeyByValue(String keyValue) {
        return apiKeyRepo.findByKeyValue(keyValue)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey k = getApiKeyById(id);
        k.setActive(false);
        apiKeyRepo.save(k);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }
}
