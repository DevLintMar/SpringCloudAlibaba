package com.lintmar.repository;

import com.lintmar.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    Borrow findBorrowByMid(Integer mid);

    List<Borrow> findBorrowByUid(Integer uid);

    List<Borrow> findBorrowByBid(Integer bid);

    Borrow findBorrowByUidAndBid(Integer uid, Integer bid);
}