package com.zhumingbei.babybei_server.constant;

import lombok.Getter;

@Getter
public enum StatusCodeConstant {
    OK(200, "操作成功"),
    UNKNOWN_ERROR(500, "服务器开小差了");
    private Integer code;
    private String msg;

    StatusCodeConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
