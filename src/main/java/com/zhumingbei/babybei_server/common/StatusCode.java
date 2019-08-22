package com.zhumingbei.babybei_server.common;

import lombok.Getter;

@Getter
public enum StatusCode {
    OK(200, "操作成功"),
    UNKNOWN_ERROR(500, "服务器开小差了");
    private Integer code;
    private String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
