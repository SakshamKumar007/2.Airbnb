package com.placement.eyadvisory.dto;

import com.placement.eyadvisory.model.EngagementStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EngagementRequest(
        @NotBlank String clientName,
        @NotBlank String sector,
        @NotBlank String projectTitle,
        @NotBlank String aiUseCase,
        @Positive BigDecimal estimatedInvestmentCrore,
        @Min(1) @Max(5) int strategicImpactScore,
        @Min(1) @Max(5) int implementationComplexityScore,
        @NotNull EngagementStatus status,
        @NotNull LocalDate targetCompletionDate,
        List<String> keyRisks) {
}
