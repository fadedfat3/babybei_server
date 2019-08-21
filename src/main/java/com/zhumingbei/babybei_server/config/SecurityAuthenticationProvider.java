package com.zhumingbei.babybei_server.config;

import com.zhumingbei.babybei_server.entity.Users;
import com.zhumingbei.babybei_server.service.impl.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SecurityAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UsersDetailsService usersDetailsService;

    public SecurityAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        Users user = (Users) usersDetailsService.loadUserByUsername(username);
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
