package com.lintmar.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author LintMar
 * @date 2022/7/29
 **/
@Data
@Entity
@Table(name = "auth_user")
public class AuthUser {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auth_user_role",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "rid"))
    private List<AuthRole> roles;
}
