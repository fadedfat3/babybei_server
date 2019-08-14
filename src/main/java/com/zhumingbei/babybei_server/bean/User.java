package com.zhumingbei.babybei_server.bean;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer lastPasswordChanged;
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getLastPasswordChanged() {
        return lastPasswordChanged;
    }

    public void setLastPasswordChanged(Integer lastPasswordChanged) {
        this.lastPasswordChanged = lastPasswordChanged;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
