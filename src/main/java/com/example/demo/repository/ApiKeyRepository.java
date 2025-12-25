package com.example.demo.repository;

import com.example.demo.entity.ApiKey;
import java.util.*;

public interface ApiKeyRepository {
    ApiKey save(ApiKey key);
    Optional<ApiKey> findById(Long id);
    Optional<ApiKey> findByKeyValue(String keyValue);
    List<ApiKey> findAll();
}
