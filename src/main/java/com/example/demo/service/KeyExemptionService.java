package com.example.demo.service;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionService {
    KeyExemption createExemption(KeyExemption exemption);
    KeyExemption getExemptionByKey(Long keyId);
}
