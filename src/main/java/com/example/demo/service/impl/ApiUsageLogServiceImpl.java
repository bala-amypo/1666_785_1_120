package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.entity.ApiKey;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.Instant;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo,
                                  ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        ApiKey key = keyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new BadRequestException("Key missing"));

        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Future timestamp");
        }

        return repo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long id) {
        return repo.findByApiKey_Id(id);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long id) {
        return repo.findForKeyBetween(id, null, null);
    }

    @Override
    public int countRequestsToday(Long id) {
        return repo.countForKeyBetween(id, null, null);
    }
}
