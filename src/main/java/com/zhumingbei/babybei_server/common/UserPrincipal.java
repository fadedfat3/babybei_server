package com.zhumingbei.babybei_server.common;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 13:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private Integer lastPasswordChanged;
    private List<Role> roleList;
    private Collection<? extends SimpleGrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        List<Permission> permissionList = user.getPermissions();
        for (Permission permission : permissionList) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getLastPasswordChanged(), user.getRoles(), simpleGrantedAuthorities);
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

    public static UserPrincipal getUserInfo() {
        Object userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userInfo instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) userInfo;
            user.setPassword("******");
            return user;
        }
        return null;
    }
}
