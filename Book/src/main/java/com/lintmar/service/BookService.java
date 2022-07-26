package com.lintmar.service;

import com.lintmar.entity.Book;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
public interface BookService {
    Book findBookByBid(Integer bid);
}
