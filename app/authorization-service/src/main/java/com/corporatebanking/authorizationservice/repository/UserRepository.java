package com.corporatebanking.authorizationservice.repository;

import com.corporatebanking.authorizationservice.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {

    public UserEntity findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public UserEntity findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }
}





