package com.lintmar.controller;

import com.alibaba.fastjson.JSONObject;
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

    @RequestMapping("/borrow/take/{uid}/{bid}")
    public JSONObject take(@PathVariable("uid") Integer uid, @PathVariable("bid") Integer bid) {
        JSONObject jsonObject = new JSONObject();
        try {
            borrowService.borrow(uid, bid);
            jsonObject.put("success", true);
            jsonObject.put("message", "借阅成功");
        } catch (RuntimeException e) {
            jsonObject.put("success", false);
            jsonObject.put("message", e.getMessage());
        }
        jsonObject.put("code", "200");
        return jsonObject;
    }

    @RequestMapping("/borrow/return/{uid}/{bid}")
    public JSONObject doReturn(@PathVariable("uid") Integer uid, @PathVariable("bid") Integer bid) {
        JSONObject jsonObject = new JSONObject();
        try {
            borrowService.doReturn(uid, bid);
            jsonObject.put("success", true);
            jsonObject.put("message", "还书成功");
        } catch (RuntimeException e) {
            jsonObject.put("success", false);
            jsonObject.put("message", e.getMessage());
        }
        jsonObject.put("code", "200");
        return jsonObject;
    }
}
