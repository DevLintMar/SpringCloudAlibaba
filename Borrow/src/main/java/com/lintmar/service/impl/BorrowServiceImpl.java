package com.lintmar.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lintmar.bean.UserBorrowDetail;
import com.lintmar.client.BookClient;
import com.lintmar.client.UserClient;
import com.lintmar.entity.Book;
import com.lintmar.entity.Borrow;
import com.lintmar.entity.User;
import com.lintmar.repository.BorrowRepository;
import com.lintmar.service.BorrowService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
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

    @Resource
    private UserClient userClient;

    @Resource
    private BookClient bookClient;

    @Override
    @SentinelResource(value = "getBorrow", blockHandler = "blocked", fallback = "fallback")
    public UserBorrowDetail getUserBorrowDetailByUid(Integer uid) {
        List<Borrow> borrowList = borrowRepository.findBorrowByUid(uid);
        User user = userClient.detail(uid);
        List<Book> bookList = borrowList.stream().map((borrow) -> bookClient.detail(borrow.getBid()))
                .collect(Collectors.toList());
        UserBorrowDetail detail = new UserBorrowDetail();
        detail.setUser(user);
        detail.setBookList(bookList);
        return detail;
    }

    public UserBorrowDetail blocked(Integer uid, BlockException e) {
        User user = new User();
        user.setUid(uid);
        user.setName("访问频率过高!");
        user.setAge(-1);
        user.setSex("未知");
        UserBorrowDetail detail = new UserBorrowDetail();
        detail.setUser(user);
        detail.setBookList(Collections.emptyList());
        return detail;
    }

    public UserBorrowDetail fallback(Integer uid) {
        User user = new User();
        user.setUid(uid);
        user.setName("服务器异常!");
        user.setAge(-1);
        user.setSex("未知");
        UserBorrowDetail detail = new UserBorrowDetail();
        detail.setUser(user);
        detail.setBookList(Collections.emptyList());
        return detail;
    }

    @Override
    @GlobalTransactional
    public boolean borrow(Integer uid, Integer bid) {
        if (userClient.bookCount(uid) == 0) throw new RuntimeException("用户借阅量不足");
        if (bookClient.count(bid) == 0) throw new RuntimeException("图书数量不足");
        if (borrowRepository.findBorrowByUidAndBid(uid, bid) != null) throw new RuntimeException("用户已经借阅该图书");
        if (!userClient.borrow(uid)) throw new RuntimeException("用户借阅时出现错误");
        if (!bookClient.borrow(bid)) throw new RuntimeException("借阅图书时出现错误");
        Borrow borrow = new Borrow();
        borrow.setUid(uid);
        borrow.setBid(bid);
        if (borrowRepository.save(borrow).getMid() == null) throw new RuntimeException("录入借阅信息时出现错误");
        return true;
    }

    @Override
    @GlobalTransactional
    public boolean doReturn(Integer uid, Integer bid) {
        if (borrowRepository.findBorrowByUidAndBid(uid, bid) == null) throw new RuntimeException("用户未借阅该图书");
        if (!userClient.doReturn(uid)) throw new RuntimeException("用户还书时出现错误");
        if (!bookClient.doReturn(bid)) throw new RuntimeException("归还图书时出现错误");
        if (borrowRepository.deleteBorrowByUidAndBid(uid, bid) == 0) throw new RuntimeException("录入归还信息时出现错误");
        return true;
    }
}
