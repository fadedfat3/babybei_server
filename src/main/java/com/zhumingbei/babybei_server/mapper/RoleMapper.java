package com.zhumingbei.babybei_server.mapper;

import com.zhumingbei.babybei_server.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @author fadedfate
 * @date Created at 2019/9/4 13:27
 */
@Repository
public interface RoleMapper {
    int insert(String name);

    Role findRoleByName(String name);
}
