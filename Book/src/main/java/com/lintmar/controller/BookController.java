package com.lintmar.controller;

import com.lintmar.entity.Book;
import com.lintmar.service.BookService;
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
public class BookController {
    @Autowired
    private BookService bookService;

    @Value("${spring.profiles.active}")
    private String environment;

    @RequestMapping("/book/{bid}")
    public Book find(@PathVariable("bid") Integer bid) {
        log.info("BookService[{}]被调用", environment);
        return bookService.findBookByBid(bid);
    }
}
