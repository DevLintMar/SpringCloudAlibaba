package com.lintmar.repository;

import com.lintmar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUid(Integer uid);
}