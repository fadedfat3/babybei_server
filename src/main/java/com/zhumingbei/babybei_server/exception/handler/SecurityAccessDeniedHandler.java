package com.zhumingbei.babybei_server.exception.handler;

import com.zhumingbei.babybei_server.common.StatusCode;
import com.zhumingbei.babybei_server.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fadedfate
 * @date Created at 2019/8/23 16:39
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.renderJson(httpServletResponse, StatusCode.ACCESS_DENIED);
    }
}
