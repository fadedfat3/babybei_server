package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.entity.Permission;
import com.zhumingbei.babybei_server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);
        String username = userService.findByUsername(s).getUsername();
        String password = userService.findByUsername(s).getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : user.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }
        return UserPrincipal.create(user);
    }
}
