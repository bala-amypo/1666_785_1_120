package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.ApiUsageLog;

public interface ApiUsageLogRepository {
    ApiUsageLog save(ApiUsageLog log);
    List<ApiUsageLog> findByApiKey_Id(Long id);
    List<ApiUsageLog> findForKeyBetween(Long id, Object a, Object b);
    int countForKeyBetween(Long id, Object a, Object b);
}
