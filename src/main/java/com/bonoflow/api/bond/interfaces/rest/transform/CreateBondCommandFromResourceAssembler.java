package com.bonoflow.api.bond.interfaces.rest.transform;

import com.bonoflow.api.bond.domain.model.commands.CreateBondCommand;
import com.bonoflow.api.bond.interfaces.rest.resources.CreateBondResource;

public class CreateBondCommandFromResourceAssembler {

    public static CreateBondCommand toCommandFromResource(CreateBondResource resource) {
        return new CreateBondCommand(
                resource.clientId(),
                resource.name(),
                resource.faceValue(),
                resource.interestRate(),
                resource.rateType(),
                resource.compounding(),
                resource.term(),
                resource.paymentFrequency(),
                resource.currency(),
                resource.graceType(),
                resource.gracePeriod()
        );
    }
}