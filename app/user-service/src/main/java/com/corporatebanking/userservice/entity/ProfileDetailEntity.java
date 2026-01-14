package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.common.BaseEntity;
import com.corporatebanking.userservice.entity.lookup.GenderEntity;
import com.corporatebanking.userservice.entity.lookup.NationalityEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Profile_detail")
public class ProfileDetailEntity extends BaseEntity {

    public String fullname;

    @Column(name = "date_of_birth")
    public LocalDateTime dateOfBirth;

    @Column(name = "phone_number", length = 20)
    public String phoneNumber;

    public String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    public GenderEntity gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id")
    public NationalityEntity nationality;

    @Column(name = "selected_account_id")
    public Integer selectedAccountId;

    public String pin;

    @Column(name = "is_policy_agreement")
    public Boolean isPolicyAgreement;

    @Column(name = "is_auto_save_receipt")
    public Boolean isAutoSaveReceipt;

    @Column(name = "has_initial_pin")
    public Boolean hasInitialPin;

    @Column(name = "organization_id")
    public Integer organizationId;

    @Column(name = "group_id")
    public Integer groupId;
}