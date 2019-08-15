package com.zhumingbei.babybei_server.service;

import com.zhumingbei.babybei_server.bean.Users;
import com.zhumingbei.babybei_server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Users getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public List<Users> getUserList() {
        return userMapper.getList();
    }
}
