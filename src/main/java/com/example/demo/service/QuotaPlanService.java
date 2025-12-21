package com.example.demo.service;
import com.example.demo.entity.QuotaPlan;
import java.util.List;

public interface QuotaPlanService {
    QuotaPlan createPlan(QuotaPlan plan); // Ensure name matches impl
    QuotaPlan getPlanById(Long id);       // Ensure name matches impl
    List<QuotaPlan> getAllPlans();
    void deletePlan(Long id);             // Missing in your current Impl
}