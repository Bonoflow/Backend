package com.bonoflow.api.bond.domain.model.aggregates;

import com.bonoflow.api.bond.domain.model.commands.CreateBondCommand;
import com.bonoflow.api.bond.domain.model.commands.UpdateBondCommand;
import com.bonoflow.api.bond.domain.model.entities.CashFlow;
import com.bonoflow.api.bond.domain.model.entities.FinancialMetric;
import com.bonoflow.api.bond.domain.model.valueobjects.BondRateType;
import com.bonoflow.api.bond.domain.model.valueobjects.BondCompounding;
import com.bonoflow.api.bond.domain.model.valueobjects.BondPaymentFrequency;
import com.bonoflow.api.bond.domain.model.valueobjects.BondGraceType;
import com.bonoflow.api.profile.domain.model.entities.Client;
import com.bonoflow.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "bonds")
public class Bond extends AuditableAbstractAggregateRoot<Bond> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(name = "face_value", nullable = false)
    private Double faceValue;

    @NotNull
    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "rate_type", nullable = false)
    private BondRateType rateType;

    @Enumerated(EnumType.STRING)
    @Column
    private BondCompounding compounding;

    @NotNull
    @Column(nullable = false)
    private Integer term;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "payment_frequency", nullable = false)
    private BondPaymentFrequency paymentFrequency;

    @NotNull
    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "grace_type", nullable = false)
    private BondGraceType graceType;

    @NotNull
    @Column(name = "grace_period", nullable = false)
    private Integer gracePeriod;

    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CashFlow> cashFlows = new ArrayList<>();

    @OneToMany(mappedBy = "bond", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinancialMetric> financialMetrics = new ArrayList<>();

    public Bond() {}

    public Bond(CreateBondCommand command, Client client) {
        this.client = client;
        this.name = command.name();
        this.faceValue = command.faceValue();
        this.interestRate = command.interestRate();
        this.rateType = BondRateType.valueOf(command.rateType().toUpperCase());
        this.compounding = BondCompounding.valueOf(command.compounding().toUpperCase());
        this.term = command.term();
        this.paymentFrequency = BondPaymentFrequency.valueOf(command.paymentFrequency().toUpperCase());
        this.currency = command.currency();
        this.graceType = BondGraceType.valueOf(command.graceType().toUpperCase());
        this.gracePeriod = command.gracePeriod();
    }

    public Bond update(UpdateBondCommand command) {
        this.name = command.name();
        this.faceValue = command.faceValue();
        this.interestRate = command.interestRate();
        this.rateType = BondRateType.valueOf(command.rateType().toUpperCase());
        this.compounding = BondCompounding.valueOf(command.compounding().toUpperCase());
        this.term = command.term();
        this.paymentFrequency = BondPaymentFrequency.valueOf(command.paymentFrequency().toUpperCase());
        this.currency = command.currency();
        this.graceType = BondGraceType.valueOf(command.graceType().toUpperCase());
        this.gracePeriod = command.gracePeriod();
        this.cashFlows.clear();
        this.financialMetrics.clear();
        return this;
    }

    public Long getClientId() {
        return client.getId();
    }
}