package com.example.demo.service.impl;

import java.time.LocalDateTime;

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
        log.setTimestamp(LocalDateTime.now()); // ✅ FIX HERE
        return repository.save(log);
    }
}
