package com.lintmar.service.impl;

import com.lintmar.entity.Book;
import com.lintmar.repository.BookRepository;
import com.lintmar.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findBookByBid(Integer bid) {
        return bookRepository.findBookByBid(bid);
    }
}
