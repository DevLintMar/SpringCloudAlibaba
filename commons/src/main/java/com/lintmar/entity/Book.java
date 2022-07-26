package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "bid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;

    @Column(name = "title")
    private String title;

    @Column(name = "desc")
    private String desc;
}
