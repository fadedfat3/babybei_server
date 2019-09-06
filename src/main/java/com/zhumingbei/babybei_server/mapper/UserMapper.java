package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUserByName(String username);

    User getUserByID(int id);
    List<User> getList();

    int insert(User user);

    void insertUserRole(Integer userID, Integer roleID);

    void updateIsMatched(int userID, int isMatched);

    void updatePartnerId(int userID, int partnerID);

    void match(int userID, int isMatched, int partnerID);
}