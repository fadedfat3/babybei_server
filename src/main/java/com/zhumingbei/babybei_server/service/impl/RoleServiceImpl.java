package com.zhumingbei.babybei_server.service.impl;

import com.zhumingbei.babybei_server.mapper.RoleMapper;
import com.zhumingbei.babybei_server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fadedfate
 * @date Created at 2019/9/4 13:38
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insert(String name) {
        return roleMapper.insert(name);
    }
}
