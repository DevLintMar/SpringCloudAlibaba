package com.lintmar.service;

import com.lintmar.entity.User;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
public interface UserService {
    User findUserByUid(Integer uid);
}
