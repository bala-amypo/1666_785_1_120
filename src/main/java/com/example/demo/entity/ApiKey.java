package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String keyValue;

    private boolean active;

    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private QuotaPlan plan;

    @OneToMany(mappedBy = "apiKey", cascade = CascadeType.ALL)
    private List<ApiUsageLog> usageLogs;

    // getters & setters
}
