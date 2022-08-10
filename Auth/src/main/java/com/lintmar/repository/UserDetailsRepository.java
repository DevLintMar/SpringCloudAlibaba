package com.lintmar.repository;

import com.lintmar.entity.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author LintMar
 * @date 2022/8/10
 **/
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Integer> {
    UserDetails findByUid(Integer uid);

    UserDetails findByUsername(String username);
}
