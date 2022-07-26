package com.lintmar.bean;

import com.lintmar.entity.Book;
import com.lintmar.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Data
public class UserBorrowDetail {
    private User user;
    private List<Book> bookList;
}
