package com.lintmar.repository;

import com.lintmar.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByBid(Integer bid);
}