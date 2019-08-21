package com.zhumingbei.babybei_server.entity;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer lastPasswordChanged;
    private List<Roles> roles;
    private List<Permissions> permissions;
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastPasswordChanged=" + lastPasswordChanged +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';
    }
}
