package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.bean.Users;
import com.zhumingbei.babybei_server.service.UserService;
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
    UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('UserList')")
    public List<Users> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{username}")
    public Users index(@PathVariable(value = "username", required = false) String username) {
        if (username == null) {
            username = "admin";
        }
        return userService.getUserByName(username);
    }
}
