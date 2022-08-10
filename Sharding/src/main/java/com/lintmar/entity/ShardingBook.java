package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
@Data
@Entity
@Table(name = "sharding_book")
public class ShardingBook {
    @Id
    @Column(name = "sid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger sid;

    @Column(name = "stitle")
    private String stitle;

    @Column(name = "sdesc")
    private String sdesc;
}
