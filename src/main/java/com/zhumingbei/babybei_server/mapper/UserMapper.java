package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUserByName(String username);

    List<User> getList();

    int insert(User user);

    void insertUserRole(Integer userID, Integer roleID);

    void updateIsMatched(int isMatched);

    void updatePartnerId(int partnerId);
}