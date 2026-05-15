package com.placement.eyadvisory.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AdvisoryEngagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String clientName;

    @NotBlank
    private String sector;

    @NotBlank
    private String projectTitle;

    @NotBlank
    private String aiUseCase;

    @Positive
    private BigDecimal estimatedInvestmentCrore;

    @Min(1)
    @Max(5)
    private int strategicImpactScore;

    @Min(1)
    @Max(5)
    private int implementationComplexityScore;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EngagementStatus status;

    @NotNull
    private LocalDate targetCompletionDate;

    @ElementCollection
    private List<String> keyRisks = new ArrayList<>();

    protected AdvisoryEngagement() {
    }

    public AdvisoryEngagement(String clientName, String sector, String projectTitle, String aiUseCase,
            BigDecimal estimatedInvestmentCrore, int strategicImpactScore, int implementationComplexityScore,
            EngagementStatus status, LocalDate targetCompletionDate, List<String> keyRisks) {
        this.clientName = clientName;
        this.sector = sector;
        this.projectTitle = projectTitle;
        this.aiUseCase = aiUseCase;
        this.estimatedInvestmentCrore = estimatedInvestmentCrore;
        this.strategicImpactScore = strategicImpactScore;
        this.implementationComplexityScore = implementationComplexityScore;
        this.status = status;
        this.targetCompletionDate = targetCompletionDate;
        this.keyRisks = keyRisks == null ? new ArrayList<>() : new ArrayList<>(keyRisks);
    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getAiUseCase() {
        return aiUseCase;
    }

    public void setAiUseCase(String aiUseCase) {
        this.aiUseCase = aiUseCase;
    }

    public BigDecimal getEstimatedInvestmentCrore() {
        return estimatedInvestmentCrore;
    }

    public void setEstimatedInvestmentCrore(BigDecimal estimatedInvestmentCrore) {
        this.estimatedInvestmentCrore = estimatedInvestmentCrore;
    }

    public int getStrategicImpactScore() {
        return strategicImpactScore;
    }

    public void setStrategicImpactScore(int strategicImpactScore) {
        this.strategicImpactScore = strategicImpactScore;
    }

    public int getImplementationComplexityScore() {
        return implementationComplexityScore;
    }

    public void setImplementationComplexityScore(int implementationComplexityScore) {
        this.implementationComplexityScore = implementationComplexityScore;
    }

    public EngagementStatus getStatus() {
        return status;
    }

    public void setStatus(EngagementStatus status) {
        this.status = status;
    }

    public LocalDate getTargetCompletionDate() {
        return targetCompletionDate;
    }

    public void setTargetCompletionDate(LocalDate targetCompletionDate) {
        this.targetCompletionDate = targetCompletionDate;
    }

    public List<String> getKeyRisks() {
        return keyRisks;
    }

    public void setKeyRisks(List<String> keyRisks) {
        this.keyRisks = keyRisks == null ? new ArrayList<>() : new ArrayList<>(keyRisks);
    }
}
