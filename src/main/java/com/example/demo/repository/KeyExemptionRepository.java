package com.example.demo.repository;

import java.util.Optional;
import com.example.demo.entity.KeyExemption;

public interface KeyExemptionRepository {
    KeyExemption save(KeyExemption e);
    Optional<KeyExemption> findByApiKey_Id(Long id);
}
