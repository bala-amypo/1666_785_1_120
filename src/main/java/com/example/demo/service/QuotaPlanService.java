package com.example.demo.service;

import com.example.demo.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {
    QuotaPlan createPlan(QuotaPlan plan);
    QuotaPlan updatePlan(Long id, QuotaPlan plan);
    QuotaPlan getPlanById(Long id);
    List<QuotaPlan> getAllPlans();
    void deletePlan(Long id);
}