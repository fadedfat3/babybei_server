package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fadedfate
 * @date Created at 2019/9/6 9:20
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    @PreAuthorize("hasAuthority('admin:list:user')")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}
