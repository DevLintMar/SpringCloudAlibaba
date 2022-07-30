package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LintMar
 * @date 2022/7/29
 **/
@Data
@Entity
@Table(name = "auth_permission")
public class AuthPermission {
    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "permission")
    private String permission;
}
