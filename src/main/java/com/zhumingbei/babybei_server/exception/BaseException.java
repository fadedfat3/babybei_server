package com.zhumingbei.babybei_server.exception;

import com.zhumingbei.babybei_server.common.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer code;
    private String msg;
    private Object data;

    public BaseException(int code, String msg, Object data) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseException(StatusCode statusCode, Object data) {
        super(statusCode.getMsg());
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    public BaseException(int code, String msg) {
        this(code, msg, null);
    }

    public BaseException(StatusCode statusCode) {
        this(statusCode, null);
    }
}
