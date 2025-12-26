package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.RateLimitEnforcement;

public interface RateLimitEnforcementRepository {
    RateLimitEnforcement save(RateLimitEnforcement e);
    Optional<RateLimitEnforcement> findById(Long id);
    List<RateLimitEnforcement> findByApiKey_Id(Long id);
}
