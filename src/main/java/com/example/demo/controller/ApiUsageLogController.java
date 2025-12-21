package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
@Tag(name = "Usage Logging")
public class ApiUsageLogController {
    private final ApiUsageLogService apiUsageLogService;

    public ApiUsageLogController(ApiUsageLogService apiUsageLogService) {
        this.apiUsageLogService = apiUsageLogService;
    }

    @PostMapping
    public ApiUsageLog logUsage(@RequestBody ApiUsageLog log) {
        return apiUsageLogService.logUsage(log);
    }

    @GET("/key/{keyId}")
    public List<ApiUsageLog> getUsageForApiKey(@PathVariable Long keyId) {
        return apiUsageLogService.getUsageForApiKey(keyId);
    }

    @GET("/key/{keyId}/today")
    public List<ApiUsageLog> getUsageForToday(@PathVariable Long keyId) {
        return apiUsageLogService.getUsageForToday(keyId);
    }

    @GET("/key/{keyId}/count-today")
    public int countRequestsToday(@PathVariable Long keyId) {
        return apiUsageLogService.countRequestsToday(keyId);
    }
}