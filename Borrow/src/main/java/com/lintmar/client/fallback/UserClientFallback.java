package com.lintmar.client.fallback;

import com.lintmar.client.UserClient;
import com.lintmar.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author LintMar
 * @date 2022/7/27
 **/
@Component
public class UserClientFallback implements UserClient {
    @Override
    public User findUserByUid(Integer uid) {
        User user = new User();
        user.setUid(uid);
        user.setName("服务器异常!");
        user.setAge(-1);
        user.setSex("未知");
        return user;
    }
}