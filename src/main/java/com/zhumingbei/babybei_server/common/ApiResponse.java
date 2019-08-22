package com.zhumingbei.babybei_server.common;

import com.zhumingbei.babybei_server.exception.BaseException;
import lombok.Data;

@Data
public class ApiResponse {
    private Integer code;
    private String msg;
    private Object data;

    public ApiResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponse(StatusCode statusCodeConstant, Object data) {
        this(statusCodeConstant.getCode(), statusCodeConstant.getMsg(), data);
    }

    public ApiResponse(StatusCode statusCodeConstant) {
        this(statusCodeConstant, null);
    }

    public ApiResponse(int code, String msg) {
        this(code, msg, null);
    }

    public ApiResponse(BaseException e, Object data) {
        this(e.getCode(), e.getMsg(), data);
    }

    public ApiResponse(BaseException e) {
        this(e, null);
    }
}
