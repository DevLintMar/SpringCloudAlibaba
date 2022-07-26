package com.lintmar.service.impl;


import com.lintmar.bean.UserBorrowDetail;
import com.lintmar.entity.Book;
import com.lintmar.entity.Borrow;
import com.lintmar.entity.User;
import com.lintmar.repository.BorrowRepository;
import com.lintmar.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(Integer uid) {
        List<Borrow> borrowList = borrowRepository.findBorrowByUid(uid);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject("http://localhost:8100/user/" + uid, User.class);
        List<Book> bookList = borrowList.stream().map((borrow) -> restTemplate.
                        getForObject("http://localhost:8200/book/" + borrow.getBid(), Book.class))
                .collect(Collectors.toList());
        UserBorrowDetail detail = new UserBorrowDetail();
        detail.setUser(user);
        detail.setBookList(bookList);
        return detail;
    }
}
