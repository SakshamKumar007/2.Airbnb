package com.placement.eyadvisory.repository;

import com.placement.eyadvisory.model.AdvisoryEngagement;
import com.placement.eyadvisory.model.EngagementStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisoryEngagementRepository extends JpaRepository<AdvisoryEngagement, Long> {

    List<AdvisoryEngagement> findBySectorIgnoreCase(String sector);

    List<AdvisoryEngagement> findByStatus(EngagementStatus status);
}
