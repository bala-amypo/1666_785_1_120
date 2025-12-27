package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    // ✅ constructor EXACTLY as tests expect
    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository,
                                  ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        log.setTimestamp(LocalDateTime.now()); // tests expect LocalDateTime
        return repository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long id) {
        return repository.findAll()
                .stream()
                .filter(l -> l.getApiKey() != null
                        && l.getApiKey().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long id) {
        LocalDate today = LocalDate.now();

        return repository.findAll()
                .stream()
                .filter(l ->
                        l.getApiKey() != null &&
                        l.getApiKey().getId().equals(id) &&
                        l.getTimestamp().toLocalDate().equals(today)
                )
                .collect(Collectors.toList());
    }

    @Override
    public int countRequestsToday(Long id) {
        return getUsageForToday(id).size();
    }
    
    
}
