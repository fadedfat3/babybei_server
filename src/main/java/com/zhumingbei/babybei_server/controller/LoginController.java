package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(String username, String password) {
        //Users user = userService.getUserByName(username);
        return "/login";
    }


}
