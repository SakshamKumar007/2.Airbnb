package com.placement.eyadvisory.config;

import com.placement.eyadvisory.model.AdvisoryEngagement;
import com.placement.eyadvisory.model.EngagementStatus;
import com.placement.eyadvisory.repository.AdvisoryEngagementRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AdvisoryEngagementRepository repository;

    public DataSeeder(AdvisoryEngagementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }

        repository.saveAll(List.of(
                new AdvisoryEngagement(
                        "Gujarat Smart Mobility Authority",
                        "Public Infrastructure",
                        "Metro investment evaluation platform",
                        "Gen AI assistant for concession agreement review",
                        BigDecimal.valueOf(420),
                        5,
                        4,
                        EngagementStatus.ANALYSIS,
                        LocalDate.now().plusMonths(4),
                        List.of("Land acquisition dependency", "Data availability for demand forecasting")),
                new AdvisoryEngagement(
                        "State Investment Promotion Board",
                        "Government Advisory",
                        "Ease of doing business reform tracker",
                        "AI copilot for regulatory simplification notes",
                        BigDecimal.valueOf(85),
                        4,
                        2,
                        EngagementStatus.PROPOSAL,
                        LocalDate.now().plusMonths(2),
                        List.of("Policy approval cycle", "Stakeholder alignment")),
                new AdvisoryEngagement(
                        "National Credit Services",
                        "Financial Services",
                        "SME credit underwriting transformation",
                        "Agentic AI workflow for document extraction and risk summaries",
                        BigDecimal.valueOf(160),
                        5,
                        3,
                        EngagementStatus.DELIVERY,
                        LocalDate.now().plusMonths(5),
                        List.of("Model explainability", "Compliance review"))));
    }
}
