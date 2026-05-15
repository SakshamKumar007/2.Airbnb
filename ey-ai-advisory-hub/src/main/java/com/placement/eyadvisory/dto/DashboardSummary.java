package com.placement.eyadvisory.dto;

import java.math.BigDecimal;
import java.util.Map;

public record DashboardSummary(
        long totalEngagements,
        BigDecimal totalInvestmentCrore,
        double averagePriorityScore,
        Map<String, Long> engagementsBySector,
        Map<String, Long> engagementsByStatus,
        EngagementResponse highestPriorityEngagement) {
}
