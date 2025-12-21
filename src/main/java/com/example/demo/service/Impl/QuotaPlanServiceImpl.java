package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {
    private final QuotaPlanRepository quotaPlanRepository;

    public QuotaPlanServiceImpl(QuotaPlanRepository quotaPlanRepository) {
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public QuotaPlan createPlan(QuotaPlan plan) {
        if (plan.getDailyLimit() != null && plan.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be > 0");
        }
        return quotaPlanRepository.save(plan);
    }

    @Override
    public QuotaPlan updatePlan(Long id, QuotaPlan plan) {
        QuotaPlan existing = getPlanById(id);
        existing.setName(plan.getName());
        existing.setDailyLimit(plan.getDailyLimit());
        existing.setActive(plan.getActive());
        return quotaPlanRepository.save(existing);
    }

    @Override
    public QuotaPlan getPlanById(Long id) {
        return quotaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanRepository.findAll();
    }

    @Override
    public void deletePlan(Long id) {
        if (!quotaPlanRepository.existsById(id)) {
            throw new ResourceNotFoundException("QuotaPlan not found");
        }
        quotaPlanRepository.deleteById(id);
    }
}