package com.zhumingbei.babybei_server.common;

import cn.hutool.core.util.StrUtil;
import com.zhumingbei.babybei_server.entity.Permission;
import com.zhumingbei.babybei_server.entity.Role;
import com.zhumingbei.babybei_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 13:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Integer userId;
    private String username;
    private String password;
    private Integer isMatched;
    private Integer partnerId;
    private List<Role> roles;
    private Set<Permission> permissions;
    private Collection<? extends SimpleGrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        Set<Permission> permissions = new HashSet<>();
        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }
        for (Permission permission : permissions) {
            if (StrUtil.isNotBlank(permission.getExprission())) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission.getExprission()));
            }
        }
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getIsMatched(), user.getPartnerId(), user.getRoles(), permissions, simpleGrantedAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static User User() {
        //读取数据库最新数据
        Object userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userInfo instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) userInfo;
            user.setPassword(null);

            return new User(user);
        }
        return null;
    }

}
