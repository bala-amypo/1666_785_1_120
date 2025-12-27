package com.example.demo.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repository;

    // ✅ Constructor injection, only the repository required
    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository) {
        this.repository = repository;
    }

    // ✅ Logs usage and sets timestamp
    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        log.setTimestamp(Instant.now()); // required by tests
        return repository.save(log);
    }

    // ✅ Returns all logs for a specific API key
    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long id) {
        return repository.findAll()
                .stream()
                .filter(log -> log.getApiKey() != null && log.getApiKey().getId().equals(id))
                .collect(Collectors.toList());
    }

    // ✅ Returns only today's logs for a specific API key
    @Override
    public List<ApiUsageLog> getUsageForToday(Long id) {
        LocalDate today = LocalDate.now();
        return repository.findAll()
                .stream()
                .filter(log -> log.getApiKey() != null
                        && log.getApiKey().getId().equals(id)
                        && log.getTimestamp() != null
                        && log.getTimestamp()
                              .atZone(ZoneId.systemDefault())
                              .toLocalDate()
                              .equals(today))
                .collect(Collectors.toList());
    }

    // ✅ Returns the count of requests for today
    @Override
    public int countRequestsToday(Long id) {
        return getUsageForToday(id).size();
    }
}
