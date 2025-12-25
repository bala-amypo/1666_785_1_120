package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import java.time.Instant;
import java.util.List;

public interface ApiUsageLogRepository {

    ApiUsageLog save(ApiUsageLog log);

    List<ApiUsageLog> findForKeyBetween(Long keyId, Instant start, Instant end);

    int countForKeyBetween(Long keyId, Instant start, Instant end);

    List<ApiUsageLog> findByApiKey_Id(Long keyId);
}
