package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.entity.Permissions;
import com.zhumingbei.babybei_server.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userService.getUserByName(s);
        String username = userService.getUserByName(s).getUsername();
        String password = userService.getUserByName(s).getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Permissions permission : user.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }
        return new User(username, password, authorities);
    }
}
