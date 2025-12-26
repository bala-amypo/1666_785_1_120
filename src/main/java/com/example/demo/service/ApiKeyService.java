package com.example.demo.service;

import com.example.demo.entity.ApiKey;
import java.util.List;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey key);
    ApiKey getApiKeyById(Long id);
    ApiKey getApiKeyByValue(String value);
    ApiKey updateApiKey(Long id, ApiKey key);   
    void deactivateApiKey(Long id);
    List<ApiKey> getAllApiKeys();
}
