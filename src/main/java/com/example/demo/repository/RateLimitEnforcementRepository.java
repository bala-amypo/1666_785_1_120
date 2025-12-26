package com.example.demo.repository;

import com.example.demo.entity.RateLimitEnforcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RateLimitEnforcementRepository extends JpaRepository<RateLimitEnforcement, Long> {
    List<RateLimitEnforcement> findAllByApiKey_Id(Long apiKeyId);
}
