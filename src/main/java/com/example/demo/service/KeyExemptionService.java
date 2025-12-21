package com.example.demo.service;

import com.example.demo.entity.KeyExemption;
import java.util.List;
import java.util.Optional;

public interface KeyExemptionService {
    KeyExemption createExemption(KeyExemption exemption); [cite: 276]
    KeyExemption updateExemption(Long id, KeyExemption exemption); [cite: 277]
    Optional<KeyExemption> getExemptionByKey(Long apiKeyId); [cite: 278]
    List<KeyExemption> getAllExemptions(); [cite: 279]
}