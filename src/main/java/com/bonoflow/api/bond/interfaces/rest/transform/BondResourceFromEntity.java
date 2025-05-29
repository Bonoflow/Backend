package com.bonoflow.api.bond.interfaces.rest.transform;

import com.bonoflow.api.bond.domain.model.aggregates.Bond;
import com.bonoflow.api.bond.interfaces.rest.resources.BondResource;

public class BondResourceFromEntity {

    public static BondResource toResourceFromEntity(Bond bond) {
        return new BondResource(
                bond.getId(),
                bond.getClientId(),
                bond.getName(),
                bond.getFaceValue(),
                bond.getInterestRate(),
                bond.getRateType().name(),
                bond.getCompounding() != null ? bond.getCompounding().name() : null,
                bond.getTerm(),
                bond.getPaymentFrequency().name(),
                bond.getCurrency(),
                bond.getGraceType().name(),
                bond.getGracePeriod()
        );
    }
}