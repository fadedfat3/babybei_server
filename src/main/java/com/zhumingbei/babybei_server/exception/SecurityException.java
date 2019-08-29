package com.zhumingbei.babybei_server.exception;

import com.zhumingbei.babybei_server.common.StatusCode;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 16:04
 */
public class SecurityException extends BaseException {
    public SecurityException(StatusCode statusCode, Object data) {
        super(statusCode, data);
    }

    public SecurityException(StatusCode statusCode) {
        super(statusCode);
    }

    public SecurityException(int code, String msg, Object data) {
        super(code, msg, data);
    }

    public SecurityException(int code, String msg) {
        this(code, msg, null);
    }
}
