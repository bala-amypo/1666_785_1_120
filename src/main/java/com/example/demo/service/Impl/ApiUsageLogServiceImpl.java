package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.ApiUsageLogService;
import java.time.*;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public ApiUsageLog logUsage(ApiUsageLog log) {
        if (log.getTimestamp().isAfter(Instant.now()))
            throw new BadRequestException("Future timestamp");

        keyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        return repo.save(log);
    }

    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        return repo.findForKeyBetween(keyId, Instant.now().minusSeconds(86400), Instant.now());
    }

    public int countRequestsToday(Long keyId) {
        return repo.countForKeyBetween(keyId, Instant.now().minusSeconds(86400), Instant.now());
    }

    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }
}
