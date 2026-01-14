package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import com.corporatebanking.userservice.entity.lookup.AccountTypeEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Account_detail")
public class AccountDetailEntity extends BaseEntity {

    @Column(name = "account_number", unique = true, nullable = false, length = 100)
    public String accountNumber;

    @Column(name = "user_id")
    public Integer userId;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    public AccountTypeEntity accountType;

    @Column(name = "group_id")
    public Integer groupId;

    @Column(name = "current_balance", precision = 19, scale = 4)
    public BigDecimal currentBalance = BigDecimal.ZERO;

    @Column(name = "role_id")
    public Integer roleId;
}