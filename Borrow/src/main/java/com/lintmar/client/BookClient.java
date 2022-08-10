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
    @RequestMapping("/detail/{bid}")
    Book detail(@PathVariable("bid") Integer bid);

    @RequestMapping("/count/{bid}")
    Integer count(@PathVariable("bid") Integer bid);

    @RequestMapping("/borrow/{bid}")
    boolean borrow(@PathVariable("bid") Integer bid);

    @RequestMapping("/return/{bid}")
    boolean doReturn(@PathVariable("bid") Integer bid);
}
