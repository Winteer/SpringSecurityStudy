package com.winter.security.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName : User
 * @Description : 用户表
 * @Author : Winter
 * @Date: 2020-09-04 08:51
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotBlank   // 应用层面
    @Column(unique = true, nullable = true) // 数据库层面
    private String username;
    @NotBlank
    private String password;

    private String permissions;

    public UserInfo buildInfo() {
        UserInfo info = new UserInfo();
        BeanUtils.copyProperties(this, info);
        return info;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasPermission(String method) {
        boolean result = false;
        if (StringUtils.equalsIgnoreCase("get", method)) {
            result = StringUtils.contains(permissions, "r");
        } else {
            result = StringUtils.contains(permissions, "w");
        }
        return result;
    }
}
