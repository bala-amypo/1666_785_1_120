package com.example.demo.entity;

public class QuotaPlan {

    private Long id;
    private String planName;
    private int dailyLimit;
    private boolean active;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public int getDailyLimit() { return dailyLimit; }
    public void setDailyLimit(int dailyLimit) { this.dailyLimit = dailyLimit; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
