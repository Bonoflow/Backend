package com.bonoflow.api.bond.domain.model.commands;

public record UpdateBondCommand(
        Long id,
        Long clientId,
        String name,
        Double faceValue,
        Double interestRate,
        String rateType,
        String compounding,
        Integer term,
        String paymentFrequency,
        String currency,
        String graceType,
        Integer gracePeriod
) {}