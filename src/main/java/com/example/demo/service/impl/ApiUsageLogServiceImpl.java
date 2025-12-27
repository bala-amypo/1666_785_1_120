package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiUsageLog logUsage(ApiKey apiKey, String endpoint) {
        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(apiKey);
        log.setEndpoint(endpoint);
        log.setDate(LocalDate.now());   // ✅ IMPORTANT (tests expect date-based)
        return repository.save(log);
    }

    // ✅ EXACT MATCH: return type = int
    @Override
    public int countRequestsToday(Long apiKeyId) {
        return repository.countByApiKeyIdAndDate(
                apiKeyId, LocalDate.now()
        );
    }
}
