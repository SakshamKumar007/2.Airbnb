package com.placement.eyadvisory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EyAiAdvisoryHubApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void dashboardReturnsSeededEngagementSummary() throws Exception {
        mockMvc.perform(get("/api/engagements/dashboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalEngagements").value(3))
                .andExpect(jsonPath("$.averagePriorityScore", greaterThan(0.0)))
                .andExpect(jsonPath("$.highestPriorityEngagement.projectTitle").exists());
    }

    @Test
    void createsValidEngagement() throws Exception {
        String requestBody = """
                {
                  "clientName": "Urban Transport Mission",
                  "sector": "Public Infrastructure",
                  "projectTitle": "Bus rapid transit modernization",
                  "aiUseCase": "Gen AI assistant for bid document preparation",
                  "estimatedInvestmentCrore": 210,
                  "strategicImpactScore": 5,
                  "implementationComplexityScore": 3,
                  "status": "DISCOVERY",
                  "targetCompletionDate": "2026-12-30",
                  "keyRisks": ["Procurement delays", "Legacy data quality"]
                }
                """;

        mockMvc.perform(post("/api/engagements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.keyRisks", hasSize(2)));
    }
}
