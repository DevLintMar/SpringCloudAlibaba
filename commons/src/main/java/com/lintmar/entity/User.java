package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LintMar
 * @date 2022/7/25
 **/
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;
}
