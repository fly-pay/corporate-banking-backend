package com.corporatebanking.authorizationservice.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntityBase {

    private static final Logger LOG = Logger.getLogger(UserEntity.class);

    @Id
    @Column(name = "user_id", length = 100)
    public String userId;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    public String email;

    @Column(name = "password_hash", length = 255)
    public String passwordHash;

    @Column(name = "first_name", length = 100)
    public String firstName;

    @Column(name = "last_name", length = 100)
    public String lastName;

    @Column(name = "phone_number", length = 50)
    public String phoneNumber;

    @Column(name = "role", length = 50)
    public String role;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    public LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    public LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}



