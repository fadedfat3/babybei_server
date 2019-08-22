package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUserByName(String username);

    List<User> getList();

    void insert(String username, String password);
}