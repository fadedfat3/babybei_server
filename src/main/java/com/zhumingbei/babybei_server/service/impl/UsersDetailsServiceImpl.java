package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.exception.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);
        //can not find user
        if (user == null) {
            throw new SecurityException(4000, "用户未注册");
        }
        return UserPrincipal.create(user);
    }
}
