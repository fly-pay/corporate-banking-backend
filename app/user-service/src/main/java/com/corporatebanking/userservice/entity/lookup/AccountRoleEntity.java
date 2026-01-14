package com.corporatebanking.userservice.entity.lookup;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "AccountRole")
public class AccountRoleEntity extends BaseEntity {
    @Column(length = 100)
    public String name;
}