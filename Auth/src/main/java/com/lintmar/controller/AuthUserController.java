package com.lintmar.controller;

import com.alibaba.fastjson.JSONObject;
import com.lintmar.entity.AuthUser;
import com.lintmar.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LintMar
 * @date 2022/7/29
 **/
@RestController
@RequestMapping("/user")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;

    @RequestMapping("/save")
    public AuthUser save(String username, String password) {
        return authUserService.save(username, password);
    }

    @RequestMapping("/find")
    public JSONObject find(Integer uid, String username) {
        AuthUser user = null;
        if (uid != null) user = authUserService.getAuthUserByUid(uid);
        else if (username != null && !username.isEmpty()) user = authUserService.getAuthUserByUsername(username);
        return authUserService.format(user);
    }
}
