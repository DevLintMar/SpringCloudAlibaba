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
    public User detail(Integer uid) {
        User user = new User();
        user.setUid(uid);
        user.setName("服务器异常!");
        user.setAge(-1);
        user.setSex("未知");
        return user;
    }

    @Override
    public Integer bookCount(Integer uid) {
        return -1;
    }

    @Override
    public boolean borrow(Integer uid) {
        return false;
    }

    @Override
    public boolean doReturn(Integer uid) {
        return false;
    }
}
