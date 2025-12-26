package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository   // optional but clear
public interface ApiUsageLogRepository
        extends JpaRepository<ApiUsageLog, Long>{


    List<ApiUsageLog> findByApiKey_Id(Long id);

    @Query("""
        SELECT l FROM ApiUsageLog l
        WHERE l.apiKey.id = :id
        AND l.timestamp BETWEEN :start AND :end
    """)
    List<ApiUsageLog> findForKeyBetween(
            Long id,
            Instant start,
            Instant end
    );

    @Query("""
        SELECT COUNT(l) FROM ApiUsageLog l
        WHERE l.apiKey.id = :id
        AND l.timestamp BETWEEN :start AND :end
    """)
    int countForKeyBetween(
            Long id,
            Instant start,
            Instant end
    );
}
