package com.zhumingbei.babybei_server.service;

import com.zhumingbei.babybei_server.bean.Permission;
import com.zhumingbei.babybei_server.bean.Role;
import com.zhumingbei.babybei_server.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByName(s);
        String username = userService.getUserByName(s).getUsername();
        String password = userService.getUserByName(s).getPassword();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoleList();

        for (Role role : roles) {
            List<Permission> permissions = role.getPermissionList();
            for (Permission permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }
        return org.springframework.security.core.userdetails.User
    }
}
