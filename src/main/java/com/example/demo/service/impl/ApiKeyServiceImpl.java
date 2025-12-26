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
    private final QuotaPlanRepository quotaRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository quotaRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.quotaRepo = quotaRepo;
    }

    @Override
    public ApiKey createApiKey(ApiKey key) {
        QuotaPlan plan = quotaRepo.findById(key.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (!plan.isActive()) {
            throw new BadRequestException("Plan inactive");
        }

        return apiKeyRepo.save(key);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    @Override
    public ApiKey getApiKeyByValue(String value) {
        return apiKeyRepo.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }
}
