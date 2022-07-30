package com.lintmar.service.impl;

import com.lintmar.entity.AuthPermission;
import com.lintmar.entity.AuthRole;
import com.lintmar.entity.AuthUser;
import com.lintmar.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LintMar
 * @date 2022/7/29
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser user = authUserRepository.findAuthUserByUsername(s);
        if (user == null) throw new RuntimeException("用户名或密码错误");
        List<AuthRole> roles = user.getRoles();
        List<AuthPermission> permissions = new ArrayList<>();
        roles.forEach(role -> {
            List<AuthPermission> permissionsFromRole = role.getPermissions();
            permissions.addAll(permissionsFromRole);
        });
        String[] roleStrings = roles.stream().map(AuthRole::getRole).toList().toArray(new String[]{});
        String[] permissionStrings = permissions.stream().map(AuthPermission::getPermission).toList().toArray(new String[]{});
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roleStrings)
                .authorities(permissionStrings)
                .build();
    }
}
