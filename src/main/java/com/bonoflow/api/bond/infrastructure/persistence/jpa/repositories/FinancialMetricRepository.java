package com.bonoflow.api.bond.infrastructure.persistence.jpa.repositories;

import com.bonoflow.api.bond.domain.model.entities.FinancialMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialMetricRepository extends JpaRepository<FinancialMetric, Long> {
    List<FinancialMetric> findByBond_Id(Long bondId);
}