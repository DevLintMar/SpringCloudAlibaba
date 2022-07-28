package com.lintmar.client;

import com.lintmar.client.fallback.BookClientFallback;
import com.lintmar.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LintMar
 * @date 2022/7/27
 **/
@FeignClient(value = "bookservice", contextId = "bookClient", fallback = BookClientFallback.class)
public interface BookClient {
    @RequestMapping("/book/{bid}")
    Book findBookByBid(@PathVariable("bid") Integer bid);

    @RequestMapping("/book/count/{bid}")
    Integer count(@PathVariable("bid") Integer bid);

    @RequestMapping("/book/borrow/{bid}")
    boolean borrow(@PathVariable("bid") Integer bid);

    @RequestMapping("/book/return/{bid}")
    boolean doReturn(@PathVariable("bid") Integer bid);
}
