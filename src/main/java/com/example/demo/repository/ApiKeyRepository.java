package com.example.demo.repository;

import com.example.demo.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByKeyValue(String keyValue);
    List<ApiKey> findByOwnerId(Long ownerId);
    List<ApiKey> findByActive(Boolean active);
    
    @Query("SELECT k FROM ApiKey k WHERE k.plan.id = :planId AND k.active = true")
    List<ApiKey> findActiveKeysByPlan(@Param("planId") Long planId);
}