package com.lintmar.service;

import com.lintmar.entity.ShardingBook;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
public interface ShardingBookService {
    ShardingBook save(ShardingBook shardingBook);

    ShardingBook findBySid(Integer sid);
}
