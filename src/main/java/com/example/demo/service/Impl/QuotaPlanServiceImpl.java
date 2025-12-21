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
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        if (plan.getDailyLimit() <= 0) throw new BadRequestException("Daily limit must be > 0");
        try {
            return quotaPlanRepository.save(plan);
        } catch (Exception e) {
            throw new IllegalArgumentException("Plan name must be unique");
        }
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return quotaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
        quotaPlanRepository.save(plan);
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanRepository.findAll();
    }
}