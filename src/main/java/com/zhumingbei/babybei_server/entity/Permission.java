package com.zhumingbei.babybei_server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    //便于验证序列化是否过期
    private static final long serialVersionUID = 4639182498515125085L;
    private Integer id;
    private String name;
    private String exprission;
    private String url;
}
