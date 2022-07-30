package com.lintmar.repository;

import com.lintmar.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    AuthUser findAuthUserByUid(Integer uid);

    AuthUser findAuthUserByUsername(String username);

    @Transactional
    int deleteAuthUserByUid(Integer uid);

    @Transactional
    int deleteAuthUserByUsername(String username);
}