package com.placement.eyadvisory.dto;

import com.placement.eyadvisory.model.EngagementStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EngagementResponse(
        Long id,
        String clientName,
        String sector,
        String projectTitle,
        String aiUseCase,
        BigDecimal estimatedInvestmentCrore,
        int strategicImpactScore,
        int implementationComplexityScore,
        EngagementStatus status,
        LocalDate targetCompletionDate,
        double priorityScore,
        List<String> keyRisks) {
}
