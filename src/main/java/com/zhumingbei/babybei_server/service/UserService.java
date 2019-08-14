package com.zhumingbei.babybei_server.service;

import com.zhumingbei.babybei_server.bean.User;
import com.zhumingbei.babybei_server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

}
