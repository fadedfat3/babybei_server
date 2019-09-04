package com.zhumingbei.babybei_server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 4826103268138326814L;
    private Integer id;
    private String name;
    private List<Permission> permissions;
}
