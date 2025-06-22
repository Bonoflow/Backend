package com.bonoflow.api.bond.interfaces.rest.resources;


import java.time.LocalDate;

public record BondResource(
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
        Integer gracePeriod,
        LocalDate issueDate,
        LocalDate maturityDate
) {
}