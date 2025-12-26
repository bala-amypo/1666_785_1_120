package com.example.demo.entity;
@Entity
public class QuotaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    public boolean isActive() {
        return active;
    }
}
