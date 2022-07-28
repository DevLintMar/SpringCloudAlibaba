package com.lintmar.repository;

import com.lintmar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUid(Integer uid);

    @Modifying
    @Transactional
    @Query("update User set bookCount = ?2 where uid = ?1")
    int updateBookCountByUid(Integer uid, Integer bookCount);
}