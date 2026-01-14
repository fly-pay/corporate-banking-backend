package com.corporatebanking.userservice.entity.lookup;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Gender") // Change table name for others
public class GenderEntity extends BaseEntity {
    public String name;
}