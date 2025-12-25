package com.example.demo.repository;

import com.example.demo.entity.KeyExemption;
import java.util.Optional;

public interface KeyExemptionRepository {
    KeyExemption save(KeyExemption exemption);
    Optional<KeyExemption> findByApiKey_Id(Long keyId);
}
