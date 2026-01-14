package com.corporatebanking.userservice.entity.lookup;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Gender")
public class GenderEntity extends BaseEntity {
    public String name;
}