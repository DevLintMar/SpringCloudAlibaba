package com.lintmar.service.impl;

import com.lintmar.entity.ShardingBook;
import com.lintmar.repository.ShardingBookRepository;
import com.lintmar.service.ShardingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
@Service
public class ShardingBookServiceImpl implements ShardingBookService {
    @Autowired
    private ShardingBookRepository shardingBookRepository;

    @Override
    public ShardingBook save(ShardingBook shardingBook) {
        return shardingBookRepository.save(shardingBook);
    }

    @Override
    public ShardingBook findBySid(Integer sid) {
        return shardingBookRepository.findBySid(sid);
    }
}
