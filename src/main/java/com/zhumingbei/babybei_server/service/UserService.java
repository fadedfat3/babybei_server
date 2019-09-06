package com.zhumingbei.babybei_server.service;

import com.zhumingbei.babybei_server.entity.User;

import java.util.List;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 13:52
 */
public interface UserService {
    User findByUsername(String username);

    User findByID(int id);
    List<User> getUserList();

    int insert(User user);

    void match(User user);

    void dismatch(User user);
}
