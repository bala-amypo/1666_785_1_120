package com.example.demo.repository;

import java.util.*;
import com.example.demo.entity.QuotaPlan;

public interface QuotaPlanRepository {
    Optional<QuotaPlan> findById(Long id);
    List<QuotaPlan> findAll();
    QuotaPlan save(QuotaPlan plan);
}
