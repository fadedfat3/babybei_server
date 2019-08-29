package com.zhumingbei.babybei_server.config;

import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.entity.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fadedfate
 * @date Created at 2019/8/29 16:25
 */
@Component
public class RbcaAuthorityConfig {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<Permission> permissions = userPrincipal.getPermissions();
        List<String> urls = new ArrayList<>();
        for (Permission permission : permissions) {
            String url = permission.getUrl();
            if (url != null && !url.isEmpty()) {
                urls.add(url);
            }
        }
        for (String url : urls) {
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(url);
            if (antPathRequestMatcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
