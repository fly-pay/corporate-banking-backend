package com.corporatebanking.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Rule_Configuration")
public class RuleConfigurationEntity {
    @Id
    public Integer id;
    public String name;

    @Column(name = "from_max_amount")
    public BigDecimal fromMaxAmount;

    @Column(name = "from_symbol")
    public String fromSymbol;

    @Column(name = "to_min_amount")
    public BigDecimal toMinAmount;

    @Column(name = "to_symbol")
    public String toSymbol;

    @Column(name = "organization_id")
    public Integer organizationId;
}