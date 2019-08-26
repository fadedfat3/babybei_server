package com.zhumingbei.babybei_server.controller;

import cn.hutool.core.lang.Dict;
import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.common.StatusCode;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.exception.SecurityException;
import com.zhumingbei.babybei_server.service.impl.UserServiceImpl;
import com.zhumingbei.babybei_server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/login")
    public String login() {
        //User user = userService.getUserByName(username);
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwtStr = jwtUtil.createJWT(authentication, false);
        return ApiResponse.ofSuccess(Dict.create().set("jwt", jwtStr));
    }

    @GetMapping("/registry")
    public String registry() {
        return "/registry";
    }

    @PostMapping("/registry")
    @ResponseBody
    public ApiResponse registry(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ApiResponse.of(6000, "用户已存在");
        }
        userService.insert(username, password);
        return ApiResponse.ofSuccess("注册成功");
    }

    @ResponseBody
    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {
        try {
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            return ApiResponse.ofException(e);
        }
        return ApiResponse.of(StatusCode.LOGOUT);
    }


}
