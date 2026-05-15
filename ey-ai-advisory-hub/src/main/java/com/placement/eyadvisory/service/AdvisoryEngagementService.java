package com.placement.eyadvisory.service;

import com.placement.eyadvisory.dto.DashboardSummary;
import com.placement.eyadvisory.dto.EngagementRequest;
import com.placement.eyadvisory.dto.EngagementResponse;
import com.placement.eyadvisory.exception.ResourceNotFoundException;
import com.placement.eyadvisory.model.AdvisoryEngagement;
import com.placement.eyadvisory.model.EngagementStatus;
import com.placement.eyadvisory.repository.AdvisoryEngagementRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvisoryEngagementService {

    private final AdvisoryEngagementRepository repository;

    public AdvisoryEngagementService(AdvisoryEngagementRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EngagementResponse> findAll(String sector, EngagementStatus status) {
        Stream<AdvisoryEngagement> stream = repository.findAll().stream();
        if (sector != null && !sector.isBlank()) {
            stream = stream.filter(item -> item.getSector().equalsIgnoreCase(sector));
        }
        if (status != null) {
            stream = stream.filter(item -> item.getStatus() == status);
        }
        return stream.map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EngagementResponse findById(Long id) {
        return repository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Engagement not found with id " + id));
    }

    public EngagementResponse create(EngagementRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public EngagementResponse update(Long id, EngagementRequest request) {
        AdvisoryEngagement engagement = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engagement not found with id " + id));
        engagement.setClientName(request.clientName());
        engagement.setSector(request.sector());
        engagement.setProjectTitle(request.projectTitle());
        engagement.setAiUseCase(request.aiUseCase());
        engagement.setEstimatedInvestmentCrore(request.estimatedInvestmentCrore());
        engagement.setStrategicImpactScore(request.strategicImpactScore());
        engagement.setImplementationComplexityScore(request.implementationComplexityScore());
        engagement.setStatus(request.status());
        engagement.setTargetCompletionDate(request.targetCompletionDate());
        engagement.setKeyRisks(request.keyRisks());
        return toResponse(engagement);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Engagement not found with id " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public DashboardSummary getDashboardSummary() {
        List<EngagementResponse> engagements = repository.findAll().stream().map(this::toResponse).toList();
        BigDecimal totalInvestment = engagements.stream()
                .map(EngagementResponse::estimatedInvestmentCrore)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        double averagePriority = engagements.stream()
                .mapToDouble(EngagementResponse::priorityScore)
                .average()
                .orElse(0);
        Map<String, Long> bySector = engagements.stream()
                .collect(Collectors.groupingBy(EngagementResponse::sector, Collectors.counting()));
        Map<String, Long> byStatus = engagements.stream()
                .collect(Collectors.groupingBy(item -> item.status().name(), Collectors.counting()));
        EngagementResponse top = engagements.stream()
                .max(Comparator.comparingDouble(EngagementResponse::priorityScore))
                .orElse(null);

        return new DashboardSummary(engagements.size(), totalInvestment, round(averagePriority), bySector, byStatus, top);
    }

    private AdvisoryEngagement toEntity(EngagementRequest request) {
        return new AdvisoryEngagement(
                request.clientName(),
                request.sector(),
                request.projectTitle(),
                request.aiUseCase(),
                request.estimatedInvestmentCrore(),
                request.strategicImpactScore(),
                request.implementationComplexityScore(),
                request.status(),
                request.targetCompletionDate(),
                request.keyRisks());
    }

    private EngagementResponse toResponse(AdvisoryEngagement engagement) {
        return new EngagementResponse(
                engagement.getId(),
                engagement.getClientName(),
                engagement.getSector(),
                engagement.getProjectTitle(),
                engagement.getAiUseCase(),
                engagement.getEstimatedInvestmentCrore(),
                engagement.getStrategicImpactScore(),
                engagement.getImplementationComplexityScore(),
                engagement.getStatus(),
                engagement.getTargetCompletionDate(),
                calculatePriorityScore(engagement),
                new ArrayList<>(engagement.getKeyRisks()));
    }

    private double calculatePriorityScore(AdvisoryEngagement engagement) {
        double investmentWeight = engagement.getEstimatedInvestmentCrore()
                .min(BigDecimal.valueOf(500))
                .divide(BigDecimal.valueOf(500), 2, RoundingMode.HALF_UP)
                .doubleValue() * 2;
        double impactWeight = engagement.getStrategicImpactScore() * 1.4;
        double complexityPenalty = engagement.getImplementationComplexityScore() * 0.6;
        return round(investmentWeight + impactWeight - complexityPenalty);
    }

    private double round(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
