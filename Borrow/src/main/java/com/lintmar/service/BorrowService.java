package com.lintmar.service;

import com.lintmar.bean.UserBorrowDetail;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
public interface BorrowService {
    UserBorrowDetail getUserBorrowDetailByUid(Integer uid);

    boolean borrow(Integer uid, Integer bid);

    boolean doReturn(Integer uid, Integer bid);
}
