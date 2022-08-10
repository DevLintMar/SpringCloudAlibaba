package com.lintmar.controller;

import com.lintmar.entity.ShardingBook;
import com.lintmar.service.ShardingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
@RestController
public class ShardingBookController {
    @Autowired
    private ShardingBookService shardingBookService;

    @RequestMapping("/save")
    public ShardingBook save(ShardingBook shardingBook) {
        if (shardingBook.getStitle() == null || shardingBook.getSdesc() == null || shardingBook.getStitle().isEmpty() || shardingBook.getSdesc().isEmpty())
            throw new RuntimeException();
        return shardingBookService.save(shardingBook);
    }

    @RequestMapping("/find")
    public ShardingBook find(Integer sid) {
        if (sid == null) throw new RuntimeException();
        return shardingBookService.findBySid(sid);
    }
}
