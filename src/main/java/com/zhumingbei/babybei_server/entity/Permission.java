package com.zhumingbei.babybei_server.entity;

import lombok.Data;

@Data
public class Permission  {
    private Long permissionId;
    private String permissionName;
    private String exprission;
    private String url;
}
