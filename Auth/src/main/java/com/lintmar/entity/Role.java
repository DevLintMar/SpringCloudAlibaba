package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LintMar
 * @date 2022/8/10
 **/
@Data
@Entity
@Table(name = "auth_role")
public class Role {
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    @Column(name = "role")
    private String role;
}
