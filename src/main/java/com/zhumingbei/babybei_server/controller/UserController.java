package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.impl.UserServiceImpl;
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
    public UserPrincipal info() {
        return UserPrincipal.getUserInfo();
    }

    @PostMapping("/other")
    @PreAuthorize("hasAuthority('admin:other:user')")
    public User otherUser(String name) {
        User user = userService.findByUsername(name);
        user.setPassword(null);
        return user;
    }
}
