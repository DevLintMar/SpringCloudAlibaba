package com.lintmar.controller;

import com.lintmar.entity.User;
import com.lintmar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${spring.profiles.active}")
    private String environment;

    @RequestMapping("/user/{uid}")
    public User find(@PathVariable("uid") Integer uid) {
        log.info("UserService[{}]被调用", environment);
        return userService.findUserByUid(uid);
    }

    @RequestMapping("/user/bookCount/{uid}")
    public Integer bookCount(@PathVariable("uid") Integer uid) {
        return userService.getBookCountByUid(uid);
    }

    @RequestMapping("/user/borrow/{uid}")
    public boolean borrow(@PathVariable("uid") Integer uid) {
        return userService.updateBookCountByUid(uid, userService.getBookCountByUid(uid) - 1);
    }

    @RequestMapping("/user/return/{uid}")
    public boolean doReturn(@PathVariable("uid") Integer uid) {
        return userService.updateBookCountByUid(uid, userService.getBookCountByUid(uid) + 1);
    }
}
