package com.lintmar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lintmar.entity.AuthPermission;
import com.lintmar.entity.AuthRole;
import com.lintmar.entity.AuthUser;
import com.lintmar.repository.AuthUserRepository;
import com.lintmar.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LintMar
 * @date 2022/7/29
 **/
@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public AuthUser save(String username, String password) {
        if (authUserRepository.findAuthUserByUsername(username) != null) throw new RuntimeException("用户名已存在");
        AuthUser user = new AuthUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        return authUserRepository.save(user);
    }

    @Override
    @Transactional
    public AuthUser getAuthUserByUid(Integer uid) {
        return authUserRepository.findAuthUserByUid(uid);
    }

    @Override
    @Transactional
    public AuthUser getAuthUserByUsername(String username) {
        return authUserRepository.findAuthUserByUsername(username);
    }

    @Override
    public int deleteAuthUserByUid(Integer uid) {
        return authUserRepository.deleteAuthUserByUid(uid);
    }

    @Override
    public int deleteAuthUserByUsername(String username) {
        return authUserRepository.deleteAuthUserByUsername(username);
    }

    @Override
    public JSONObject format(AuthUser user) {
        JSONObject jsonObject = new JSONObject();
        if (user == null) jsonObject.put("Error", "找不到用户");
        else {
            List<AuthRole> roleList = user.getRoles();
            List<String> roles = roleList.stream().map(AuthRole::getRole).toList();
            List<AuthPermission> permissionList = new ArrayList<>();
            roleList.forEach(role -> permissionList.addAll(role.getPermissions()));
            List<String> permissions = permissionList.stream().map(AuthPermission::getPermission).toList();
            jsonObject.put("uid", user.getUid());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("roles", roles);
            jsonObject.put("permissions", permissions);
        }
        return jsonObject;
    }
}
