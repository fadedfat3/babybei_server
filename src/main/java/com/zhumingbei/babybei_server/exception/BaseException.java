package com.zhumingbei.babybei_server.exception;

import com.zhumingbei.babybei_server.constant.StatusCodeConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer code;
    private String msg;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(StatusCodeConstant statusCodeConstant) {
        super(statusCodeConstant.getMsg());
        this.code = statusCodeConstant.getCode();
        this.msg = statusCodeConstant.getMsg();
    }
}
