package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.bean.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    Users getUserByName(String username);

    List<Users> getList();
}