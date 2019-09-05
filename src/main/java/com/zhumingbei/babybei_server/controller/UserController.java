package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.exception.AuthenticationException;
import com.zhumingbei.babybei_server.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:list:user')")
    public List<User> getUserList() {
        return userService.getUserList();
    }


    @GetMapping("/me")
    public User info() {
        return UserPrincipal.User();
    }

    @PostMapping("/other")
    @PreAuthorize("hasAuthority('admin:other:user')")
    public User otherUser(String name) {
        if (name.equals("admin-test")) {
            //直接抛出异常，系统打印异常追踪信息，handler进行后续处理
            // 若用try catch捕获异常，异常不会被抛出，相当于程序处理异常，handler不会处理
            throw new AuthenticationException(50000, "test");
        }
        User user = userService.findByUsername(name);
        user.setPassword(null);
        return user;
    }


}
