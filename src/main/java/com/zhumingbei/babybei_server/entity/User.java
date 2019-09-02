package com.zhumingbei.babybei_server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8592740736423969002L;
    private Long userId;
    private String username;
    private String password;
    private Integer lastPasswordChanged;
    private List<Role> roles;
    private List<Permission> permissions;

}
