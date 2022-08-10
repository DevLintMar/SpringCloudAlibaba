package com.lintmar.repository;

import com.lintmar.entity.ShardingBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
public interface ShardingBookRepository extends JpaRepository<ShardingBook, Integer> {
    ShardingBook findBySid(Integer sid);
}
