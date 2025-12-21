package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
@Tag(name = "Key Exemptions") [cite: 473]
public class KeyExemptionController {
    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    } [cite: 399]

    @PostMapping
    public KeyExemption createExemption(@RequestBody KeyExemption exemption) {
        return exemptionService.createExemption(exemption); [cite: 403]
    }

    @GetMapping("/key/{keyId}")
    public KeyExemption getByApiKey(@PathVariable Long keyId) {
        return exemptionService.getExemptionByKey(keyId).orElse(null); [cite: 412]
    }

    @GetMapping
    public List<KeyExemption> getAll() {
        return exemptionService.getAllExemptions(); [cite: 415]
    }
}