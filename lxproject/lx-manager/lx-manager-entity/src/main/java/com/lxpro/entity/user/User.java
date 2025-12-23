package com.lxpro.entity.user;

import java.io.Serializable;

/**
 * 用户实体（最小可用）。
 *
 * 注意：字段与 getter 名称需满足现有代码调用（例如 {@code getUserStatus()}）。
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String password;
    private Integer userStatus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}

