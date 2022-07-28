package com.lintmar.repository;

import com.lintmar.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByBid(Integer bid);

    @Modifying
    @Transactional
    @Query(value = "update Book set count = ?2 where bid = ?1", nativeQuery = true)
    int updateCountByBid(Integer bid, Integer count);
}