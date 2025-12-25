package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;

public class ApiKeyController {

    private ApiKeyService apiKeyService;

    public ApiKey updateApiKey(Long id, ApiKey key) {
        return apiKeyService.updateApiKey(id, key);
    }
}
