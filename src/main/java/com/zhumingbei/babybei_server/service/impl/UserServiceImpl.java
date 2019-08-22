package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.mapper.UserMapper;
import com.zhumingbei.babybei_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUsername(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getList();
    }

    @Override
    public void insert(String username, String password) {
        userMapper.insert(username, new BCryptPasswordEncoder().encode(password));
    }
}
