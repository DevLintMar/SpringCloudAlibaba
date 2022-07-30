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
@Table(name = "auth_role")
public class AuthRole {
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auth_role_permission",
            joinColumns = @JoinColumn(name = "rid"),
            inverseJoinColumns = @JoinColumn(name = "pid"))
    private List<AuthPermission> permissions;
}
