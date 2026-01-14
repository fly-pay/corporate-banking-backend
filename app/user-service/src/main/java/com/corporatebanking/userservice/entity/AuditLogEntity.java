package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "Audit_log")
public class AuditLogEntity extends BaseEntity {

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "before_state")
    public Map<String, Object> beforeState;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "after_state")
    public Map<String, Object> afterState;

    public String action;
}