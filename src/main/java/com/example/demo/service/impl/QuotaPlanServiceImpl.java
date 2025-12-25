package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.*;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import java.util.List;

public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    public QuotaPlan createQuotaPlan(QuotaPlan p) {
        if (p.getDailyLimit() <= 0)
            throw new BadRequestException("Invalid limit");
        return repo.save(p);
    }

    public QuotaPlan getQuotaPlanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public void deactivateQuotaPlan(Long id) {
        QuotaPlan p = getQuotaPlanById(id);
        p.setActive(false);
    }

    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan updated) {
        QuotaPlan existing = getQuotaPlanById(id);
        existing.setPlanName(updated.getPlanName());
        existing.setDailyLimit(updated.getDailyLimit());
        return repo.save(existing);
    }

    public List<QuotaPlan> getAllPlans() {
        return repo.findAll();
    }
}
