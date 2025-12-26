package com.example.demo.repository;

import java.util.*;
import java.util.List;
import com.example.demo.entity.ApiKey;

public interface ApiKeyRepository {
     Optional<ApiKey>findById(Long id);
     Optional<ApiKey>findByKeyValue(String value);
    List<ApiKey> findAll();
    ApiKey save(ApiKey key);
}
