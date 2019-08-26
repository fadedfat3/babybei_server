package com.zhumingbei.babybei_server.util;

import cn.hutool.core.util.StrUtil;
import com.zhumingbei.babybei_server.common.StatusCode;
import com.zhumingbei.babybei_server.common.UserPrincipal;
import com.zhumingbei.babybei_server.config.JwtConfig;
import com.zhumingbei.babybei_server.exception.SecurityException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 13:34
 */
@Component
@Slf4j
public class JwtUtil {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Long ttl;
    public String createJWT(UserPrincipal user, Boolean rememberMe) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(user.getUserId().toString())
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                .claim("roles", user.getRoleList())
                .claim("authorities", user.getAuthorities());
        // 设置过期时间
        ttl = rememberMe ? jwtConfig.getRemember() : jwtConfig.getTtl();

        String jwt = builder.compact();
        redisTemplate.opsForValue().set(jwtConfig.getRedisKeyPrefix() + user.getUsername(), jwt, ttl, TimeUnit.SECONDS);
        return jwt;
    }

    public String createJWT(Authentication authentication, Boolean rememberMe) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return createJWT(userPrincipal, rememberMe);
    }

    public Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getKey())
                    .parseClaimsJws(jwt)
                    .getBody();
            String username = claims.getSubject();
            String key = jwtConfig.getRedisKeyPrefix() + username;
            Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (expire == null || expire < 0) {
                throw new SecurityException(StatusCode.TOKEN_EXPIRED);
            }
            String redisJwt = (String) redisTemplate.opsForValue().get(key);

            if (!redisJwt.equals(jwt)) {
                log.error("jwt:{}", jwt);
                log.error("redisJwt:{}", redisJwt);
                throw new SecurityException(StatusCode.TOKEN_OUT_OF_CTRL);
            }
            refreshExpire(key, ttl);
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw new SecurityException(StatusCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new SecurityException(StatusCode.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new SecurityException(StatusCode.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw new SecurityException(StatusCode.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new SecurityException(StatusCode.TOKEN_PARSE_ERROR);
        }

    }

    public void refreshExpire(String key, Long second) {
        redisTemplate.expire(key, second, TimeUnit.SECONDS);
    }

    /**
     * 设置JWT过期
     *
     * @param request 请求
     */
    public void invalidateJWT(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJWT(jwt);
        // 从redis中清除JWT
        redisTemplate.delete(jwtConfig.getRedisKeyPrefix() + username);
    }

    /**
     * 根据 jwt 获取用户名
     *
     * @param jwt JWT
     * @return 用户名
     */
    public String getUsernameFromJWT(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    /**
     * 从 request 的 header 中获取 JWT
     *
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
