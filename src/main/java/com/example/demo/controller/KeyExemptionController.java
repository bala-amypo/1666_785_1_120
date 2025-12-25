package com.example.demo.controller;

import com.example.demo.service.KeyExemptionService;

public class KeyExemptionController {

    private KeyExemptionService exemptionService;

    public Iterable<?> getAll() {
        return exemptionService.getAllExemptions();
    }
}
