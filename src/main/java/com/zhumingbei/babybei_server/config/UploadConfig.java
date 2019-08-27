package com.zhumingbei.babybei_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties("upload")
@Data
public class UploadConfig {
    private String path;

}
