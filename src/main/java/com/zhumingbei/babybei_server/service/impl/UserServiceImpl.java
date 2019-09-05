package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.entity.Role;
import com.zhumingbei.babybei_server.entity.User;
import com.zhumingbei.babybei_server.mapper.RoleMapper;
import com.zhumingbei.babybei_server.mapper.UserMapper;
import com.zhumingbei.babybei_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    //@Cacheable(value = "user", key = "#name" , unless = "#result == null") //必须使用spel
    public User findByUsername(String name) {

        User user = userMapper.getUserByName(name);
        if (user == null) {
            return null;
        }
        List<Role> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            String roleName = role.getName();
            roles.add(roleMapper.findRoleByName(roleName));
        }
        user.setRoles(roles);
        return user;
    }

    @Override
    //@Cacheable(value = "user", unless = "#result == null")
    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        for (User user : userMapper.getList()) {
            user.setPassword(null);
            List<Role> roles = new ArrayList<>();
            for (Role role : user.getRoles()) {
                roles.add(roleMapper.findRoleByName(role.getName()));
            }
            user.setRoles(roles);
            users.add(user);
        }
        return users;
    }

    public int registry(String username, String password, List<String> roleNameList) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        userMapper.insert(user);
        int userID = user.getId();
        for (String roleName : roleNameList) {
            Role role = roleMapper.findRoleByName(roleName);
            userMapper.insertUserRole(userID, role.getId());
        }
        return userID;
    }

    public int registry(String username, String password, String roleName) {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(roleName);
        return registry(username, password, roleNames);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public void match(User user) {
        userMapper.match(user.getId(), user.getIsMatched(), user.getPartnerId());
        userMapper.match(user.getPartnerId(), user.getIsMatched(), user.getId());
    }

    @Override
    public void dismatch(User user) {
        userMapper.updateIsMatched(user.getId(), user.getIsMatched());
        userMapper.updateIsMatched(user.getPartnerId(), user.getIsMatched());
    }
}
