package com.lintmar.controller;

import com.lintmar.entity.User;
import com.lintmar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/{uid}")
    public User find(@PathVariable("uid") Integer uid) {
        return userService.findUserByUid(uid);
    }
}
