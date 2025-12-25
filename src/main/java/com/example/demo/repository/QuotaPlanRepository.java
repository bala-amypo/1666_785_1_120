package com.example.demo.repository;

import com.example.demo.entity.QuotaPlan;
import java.util.*;

public interface QuotaPlanRepository {
    QuotaPlan save(QuotaPlan plan);
    Optional<QuotaPlan> findById(Long id);
    List<QuotaPlan> findAll();
}
