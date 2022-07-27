package com.lintmar.controller;

import com.lintmar.bean.UserBorrowDetail;
import com.lintmar.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Slf4j
@RefreshScope
@RestController
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @Value("${spring.profiles.active}")
    private String environment;

    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail borrow(@PathVariable("uid") Integer uid) {
        log.info("BorrowService[{}]被调用", environment);
        return borrowService.getUserBorrowDetailByUid(uid);
    }
}
