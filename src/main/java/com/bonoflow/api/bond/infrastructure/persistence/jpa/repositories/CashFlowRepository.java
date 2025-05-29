package com.bonoflow.api.bond.infrastructure.persistence.jpa.repositories;

import com.bonoflow.api.bond.domain.model.entities.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {
    List<CashFlow> findByBond_Id(Long bondId);
}