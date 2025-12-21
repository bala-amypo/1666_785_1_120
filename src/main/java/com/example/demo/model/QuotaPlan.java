package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "quota_plan", uniqueConstraints = @UniqueConstraint(columnNames = "plan_name"))
public class QuotaPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String planName; 

    @Column(nullable = false)
    @Min(value = 1, message = "Daily limit must be greater than 0")
    private Integer dailyLimit; 

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean active = true;


}