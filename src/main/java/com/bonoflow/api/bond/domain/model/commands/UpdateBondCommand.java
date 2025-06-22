package com.bonoflow.api.bond.domain.model.commands;

import java.time.LocalDate;

public record UpdateBondCommand(
        Long id,
        String name,
        Double faceValue,
        Double interestRate,
        String rateType,
        String compounding,
        Integer term,
        String paymentFrequency,
        String currency,
        String graceType,
        Integer gracePeriod,
        LocalDate issueDate,
        LocalDate maturityDate
) {}