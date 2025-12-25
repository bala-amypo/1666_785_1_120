package com.example.demo.repository;

import com.example.demo.entity.RateLimitEnforcement;
import java.util.*;

public interface RateLimitEnforcementRepository {
    RateLimitEnforcement save(RateLimitEnforcement enforcement);
    Optional<RateLimitEnforcement> findById(Long id);
    List<RateLimitEnforcement> findByApiKey_Id(Long keyId);
}
