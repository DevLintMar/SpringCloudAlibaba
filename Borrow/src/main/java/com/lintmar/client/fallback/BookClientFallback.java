package com.lintmar.client.fallback;

import com.lintmar.client.BookClient;
import com.lintmar.entity.Book;
import org.springframework.stereotype.Component;

/**
 * @author LintMar
 * @date 2022/7/27
 **/
@Component
public class BookClientFallback implements BookClient {
    @Override
    public Book detail(Integer bid) {
        Book book = new Book();
        book.setBid(bid);
        book.setTitle("服务器异常!");
        book.setDesc("请联系管理员");
        return book;
    }

    @Override
    public Integer count(Integer bid) {
        return -1;
    }

    @Override
    public boolean borrow(Integer bid) {
        return false;
    }

    @Override
    public boolean doReturn(Integer bid) {
        return false;
    }
}
