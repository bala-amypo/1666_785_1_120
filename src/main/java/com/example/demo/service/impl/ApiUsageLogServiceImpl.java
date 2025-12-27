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

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository) {
        this.repository = repository;
    }

    // ✅ Interface method 1 (FIXED)
    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        log.setTimestamp(Instant.now());   // ✅ REQUIRED BY TESTS
        return repository.save(log);
    }

    // ✅ Interface method 2
    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long id) {
        return repository.findAll()
                .stream()
                .filter(log ->
                        log.getApiKey() != null &&
                        log.getApiKey().getId().equals(id))
                .collect(Collectors.toList());
    }

    // ✅ Interface method 3
    @Override
    public List<ApiUsageLog> getUsageForToday(Long id) {
        LocalDate today = LocalDate.now();

        return repository.findAll()
                .stream()
                .filter(log ->
                        log.getApiKey() != null &&
                        log.getApiKey().getId().equals(id) &&
                        log.getTimestamp() != null &&   // safety
                        log.getTimestamp()
                           .atZone(ZoneId.systemDefault())
                           .toLocalDate()
                           .equals(today)
                )
                .collect(Collectors.toList());
    }

    // ✅ Interface method 4
    @Override
    public int countRequestsToday(Long id) {
        return getUsageForToday(id).size();
    }
}
