package com.zhumingbei.babybei_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fadedfate
 * @date Created at 2019/8/22 15:25
 */
@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {
    private String key;
    private String type = "Bearer";
    private Long ttl;
    private Long remember;
    private String redisKeyPrefix;
}
