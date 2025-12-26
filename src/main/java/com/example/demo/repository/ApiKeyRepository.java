package com.example.demo.repository;

import java.util.*;
import java.util.List;


public interface ApiKeyRepository {
    findById(Long id);
    findByKeyValue(String value);
    List<ApiKey> findAll();
    ApiKey save(ApiKey key);
}
