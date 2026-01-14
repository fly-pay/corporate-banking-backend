package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Transaction")
public class TransactionEntity extends BaseEntity {

    @Column(name = "credit_account_id")
    public Integer creditAccountId;

    @Column(name = "debit_account_id")
    public Integer debitAccountId;

    @Column(precision = 19, scale = 4)
    public BigDecimal amount;

    @Column(name = "transaction_type")
    public Integer transactionType;

    // Corporate fields
    @Column(name = "maker_id")
    public Integer makerId;

    @Column(name = "checker_id")
    public Integer checkerId;
}