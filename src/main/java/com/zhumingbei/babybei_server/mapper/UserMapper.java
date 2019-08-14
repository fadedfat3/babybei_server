package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public User getUserByName(String username);
}