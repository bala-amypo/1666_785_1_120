package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repository;

    public ApiKeyServiceImpl(ApiKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiKey create(ApiKey key) {

        QuotaPlan plan = key.getPlan();
        if (plan == null || !plan.isActive()) {
            throw new RuntimeException("Invalid or inactive quota plan");
        }

        key.setActive(true);
        return repository.save(key);
    }

    @Override
    public ApiKey getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("API Key not found"));
    }

    @Override
    public List<ApiKey> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        ApiKey key = getById(id);
        key.setActive(false);
        repository.save(key);
    }
}
