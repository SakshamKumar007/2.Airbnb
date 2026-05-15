package com.placement.eyadvisory.controller;

import com.placement.eyadvisory.dto.DashboardSummary;
import com.placement.eyadvisory.dto.EngagementRequest;
import com.placement.eyadvisory.dto.EngagementResponse;
import com.placement.eyadvisory.model.EngagementStatus;
import com.placement.eyadvisory.service.AdvisoryEngagementService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/engagements")
public class AdvisoryEngagementController {

    private final AdvisoryEngagementService service;

    public AdvisoryEngagementController(AdvisoryEngagementService service) {
        this.service = service;
    }

    @GetMapping
    public List<EngagementResponse> getAll(
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) EngagementStatus status) {
        return service.findAll(sector, status);
    }

    @GetMapping("/{id}")
    public EngagementResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EngagementResponse create(@Valid @RequestBody EngagementRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public EngagementResponse update(@PathVariable Long id, @Valid @RequestBody EngagementRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/dashboard")
    public DashboardSummary dashboard() {
        return service.getDashboardSummary();
    }
}
