package com.lintmar.controller;

import com.lintmar.entity.Book;
import com.lintmar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/book/{bid}")
    public Book find(@PathVariable("bid") Integer bid) {
        return bookService.findBookByBid(bid);
    }
}
