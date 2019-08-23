package com.zhumingbei.babybei_server.exception.handler;

import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.common.StatusCode;
import com.zhumingbei.babybei_server.exception.BaseException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ResponseBody
    @ExceptionHandler
    public ApiResponse baseExceptionHandler(BaseException e) {
        return new ApiResponse(e);
    }

    @ResponseBody
    @ExceptionHandler
    public ApiResponse AuthExceptionHandler(AuthenticationException e) {
        return ApiResponse.of(StatusCode.USERNAME_PASSWORD_ERROR);
    }
}
