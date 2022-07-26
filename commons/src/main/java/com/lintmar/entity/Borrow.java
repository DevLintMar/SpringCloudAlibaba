package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Data
@Entity
@Table(name = "borrow")
public class Borrow {
    @Id
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "bid")
    private Integer bid;
}
