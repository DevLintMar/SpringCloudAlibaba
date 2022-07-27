package com.lintmar.client;

import com.lintmar.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LintMar
 * @date 2022/7/27
 **/
@FeignClient("userservice")
public interface UserClient {
    @RequestMapping("/user/{uid}")
    User findUserByUid(@PathVariable("uid") Integer uid);
}
