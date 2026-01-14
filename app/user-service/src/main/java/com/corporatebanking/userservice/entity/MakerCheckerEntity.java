package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.lookup.AccountRoleEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "Maker_Checker")
public class MakerCheckerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "user_id")
    public Integer userId;

    @ManyToOne
    @JoinColumn(name = "account_role_id")
    public AccountRoleEntity accountRole;

    @ManyToOne
    @JoinColumn(name = "rule_configuration_id")
    public RuleConfigurationEntity ruleConfiguration;
}