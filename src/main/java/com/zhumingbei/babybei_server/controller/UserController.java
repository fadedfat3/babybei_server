package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('UserList')")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{username}")
    public User index(@PathVariable(value = "username", required = false) String username) {
        if (username == null) {
            username = "admin";
        }
        return userService.findByUsername(username);
    }
}
