package com.lintmar.service.impl;

import com.lintmar.entity.User;
import com.lintmar.repository.UserRepository;
import com.lintmar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUid(Integer uid) {
        return userRepository.findUserByUid(uid);
    }
}
