package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Organization")
public class OrganizationEntity extends BaseEntity {

    @Column(nullable = false)
    public String name;

    @Column(unique = true, length = 50)
    public String shortcode;

    @Column(nullable = false)
    public String address;

    @Column(nullable = false)
    public String country;
}