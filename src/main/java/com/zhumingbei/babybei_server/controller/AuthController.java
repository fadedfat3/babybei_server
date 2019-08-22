package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String login(String username, String password) {
        //User user = userService.getUserByName(username);
        return "/login";
    }

    @GetMapping("/registry")
    public String index() {
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


}
