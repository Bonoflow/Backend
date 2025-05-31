package com.bonoflow.api.bond.interfaces.rest.resources;

public record UpdateBondResource(
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
) {
}