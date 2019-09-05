package com.zhumingbei.babybei_server.controller;

import cn.hutool.core.lang.Dict;
import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.common.MatchCode;
import com.zhumingbei.babybei_server.common.StatusCode;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.MatchCodeService;
import com.zhumingbei.babybei_server.service.impl.UserServiceImpl;
import com.zhumingbei.babybei_server.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MatchCodeService matchCodeService;
    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String jwtStr = jwtUtil.createJWT(authentication, false);
        return ApiResponse.ofSuccess(Dict.create().set("jwt", jwtStr));
    }


    @PostMapping("/registry")
    @ResponseBody
    public ApiResponse registry(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ApiResponse.of(4100, "用户名已被注册");
        }
        String roleName = "user";
        int userID = userService.registry(username, password, roleName);
        if (userID <= 1) {
            return ApiResponse.of(500, "insert result ID" + userID);
        }
        MatchCode matchCode = matchCodeService.refresh(userID);
        return ApiResponse.ofSuccess("注册成功", Dict.create().set("matchCode", matchCode).set("info", user));
    }

    @ResponseBody
    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request) {

        jwtUtil.invalidateJWT(request);

        return ApiResponse.of(StatusCode.LOGOUT);
    }


}
