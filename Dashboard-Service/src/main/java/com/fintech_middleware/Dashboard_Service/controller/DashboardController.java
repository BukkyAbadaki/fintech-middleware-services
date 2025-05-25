package com.fintech_middleware.Dashboard_Service.controller;

import com.fintech_middleware.Dashboard_Service.dto.response.DashboardResponseDto;
import com.fintech_middleware.Dashboard_Service.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<DashboardResponseDto> getDashboard(@PathVariable String email) {
        DashboardResponseDto dashboard = dashboardService.getDashboard(email);
        return ResponseEntity.ok(dashboard);
    }
}
