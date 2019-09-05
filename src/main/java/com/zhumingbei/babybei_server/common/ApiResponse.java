package com.zhumingbei.babybei_server.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhumingbei.babybei_server.exception.BaseException;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static ApiResponse of(int code, String msg, Object data) {
        return new ApiResponse(code, msg, data);
    }

    public static ApiResponse of(int code, String msg) {
        return of(code, msg, null);
    }

    public static ApiResponse of(StatusCode statusCode, Object data) {
        return new ApiResponse(statusCode, data);
    }

    public static ApiResponse of(StatusCode statusCode) {
        return of(statusCode, null);
    }
    public static ApiResponse ofSuccess(String msg, Object data) {
        return of(200, msg, data);
    }

    public static ApiResponse ofSuccess(Object data) {
        return of(StatusCode.OK, data);
    }

    public static ApiResponse ofSuccess() {
        return ofSuccess(null);
    }

    public static ApiResponse ofSuccess(String msg) {
        return ofSuccess(msg, null);
    }

    public static <T extends BaseException> ApiResponse ofException(T t) {
        return of(t.getCode(), t.getMsg(), t.getData());
    }
}
