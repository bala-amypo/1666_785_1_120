package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {
    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    // Use Constructor Injection as required by project rules
    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepository, ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        // Validate temporary extension limit must be >= 0
        if (exemption.getTemporaryExtensionLimit() != null && exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid extension limit");
        }
        // Validate validUntil must be in the future
        if (exemption.getValidUntil() != null && exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }
        // Verify the associated API key exists
        if (exemption.getApiKey() == null || !apiKeyRepository.existsById(exemption.getApiKey().getId())) {
            throw new ResourceNotFoundException("ApiKey not found");
        }
        return exemptionRepository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {
        // Fetch existing exemption or throw exception if not found
        KeyExemption existing = exemptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));
        
        // Ensure validation is applied during update
        if (exemption.getTemporaryExtensionLimit() != null && exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid extension limit");
        }
        if (exemption.getValidUntil() != null && exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }

        // Update fields with new values
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());
        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        
        return exemptionRepository.save(existing);
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long apiKeyId) {
        return exemptionRepository.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepository.findAll();
    }
}