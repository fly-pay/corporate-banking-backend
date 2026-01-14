package com.corporatebanking.userservice.entity;

import com.corporatebanking.userservice.entity.lookup.FaqCategoryEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Faq")
public class FaqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String question;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String answer;

    @ManyToOne
    @JoinColumn(name = "faq_category_id")
    public FaqCategoryEntity category;

    public LocalDateTime created_at;
    public LocalDateTime updated_at;
}