package com.lintmar.service;

import com.alibaba.fastjson.JSONObject;
import com.lintmar.entity.AuthUser;

/**
 * @author LintMar
 * @date 2022/7/29
 **/
public interface AuthUserService {
    AuthUser save(String username, String password);

    AuthUser getAuthUserByUid(Integer uid);

    AuthUser getAuthUserByUsername(String username);

    int deleteAuthUserByUid(Integer uid);

    int deleteAuthUserByUsername(String username);

    JSONObject format(AuthUser user);
}
