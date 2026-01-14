package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "Audit_log")
public class AuditLogEntity extends BaseEntity {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "before_state")
    public String beforeState; // Or Map<String, Object>

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "after_state")
    public String afterState;

    public String action;
}