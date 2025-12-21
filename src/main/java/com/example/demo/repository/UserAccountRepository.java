package com.example.demo.repository;
import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByKeyValue(String keyValue);
}

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {
    List<ApiUsageLog> findByApiKey_Id(Long id);
    
    @Query("SELECT u FROM ApiUsageLog u WHERE u.apiKey.id = ?1 AND u.timestamp >= ?2 AND u.timestamp <= ?3")
    List<ApiUsageLog> findForKeyBetween(Long keyId, Instant start, Instant end);

    @Query("SELECT COUNT(u) FROM ApiUsageLog u WHERE u.apiKey.id = ?1 AND u.timestamp >= ?2 AND u.timestamp <= ?3")
    int countForKeyBetween(Long keyId, Instant start, Instant end);
}