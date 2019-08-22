package com.zhumingbei.babybei_server.exception.handler;

import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.exception.BaseException;
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
}
