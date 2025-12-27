package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository logRepo;
    private final ApiKeyRepository apiKeyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository logRepo,
                                  ApiKeyRepository apiKeyRepo) {
        this.logRepo = logRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public ApiUsageLog logUsage(Long apiKeyId) {

        ApiKey key = apiKeyRepo.findById(apiKeyId)
                .orElseThrow(() -> new RuntimeException("API Key not found"));

        ApiUsageLog log = new ApiUsageLog();
        log.setApiKey(key);
        log.setTimestamp(LocalDateTime.now());

        return logRepo.save(log);
    }

    @Override
    public List<ApiUsageLog> getLogsForKey(Long apiKeyId) {
        return logRepo.findByApiKeyId(apiKeyId);
    }
}
