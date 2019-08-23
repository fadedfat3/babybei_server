package com.zhumingbei.babybei_server.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhumingbei.babybei_server.common.Jwt;
import com.zhumingbei.babybei_server.common.StatusCode;
import com.zhumingbei.babybei_server.exception.BaseException;
import com.zhumingbei.babybei_server.service.impl.UsersDetailsServiceImpl;
import com.zhumingbei.babybei_server.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 16:23
 */

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UsersDetailsServiceImpl userDetailsService;

    @Autowired
    private Jwt jwt;

    @Autowired
    private UrlIgnoreConfig urlIgnoreConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (checkIgnores(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtStr = jwt.getJwtFromRequest(request);

        if (StrUtil.isNotBlank(jwtStr)) {
            try {
                String username = jwt.getUsernameFromJWT(jwtStr);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (BaseException e) {
                ResponseUtil.renderJson(response, e);
            }
        } else {
            ResponseUtil.renderJson(response, StatusCode.UNAUTHORIZED, null);
        }

    }

    /**
     * 请求是否不需要进行权限拦截
     *
     * @param request 当前请求
     * @return true - 忽略，false - 不忽略
     */
    private boolean checkIgnores(HttpServletRequest request) {
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.resolve(method);
        if (ObjectUtil.isNull(httpMethod)) {
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = new HashSet();

        switch (httpMethod) {
            case GET:
                ignores.addAll(urlIgnoreConfig
                        .getGet());
                break;
            case PUT:
                ignores.addAll(urlIgnoreConfig
                        .getPut());
                break;
            case POST:
                ignores.addAll(urlIgnoreConfig
                        .getPost());
                break;
            case DELETE:
                ignores.addAll(urlIgnoreConfig
                        .getDelete());
                break;
            default:
                break;
        }

        ignores.addAll(urlIgnoreConfig
                .getPattern());

        if (CollUtil.isNotEmpty(ignores)) {
            for (String ignore : ignores) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request)) {
                    return true;
                }
            }
        }

        return false;
    }

}
