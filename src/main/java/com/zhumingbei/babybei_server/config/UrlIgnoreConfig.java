package com.zhumingbei.babybei_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * @author fadedfate
 * @date Created at 2019/8/23 16:17
 */
@Configuration
@ConfigurationProperties(prefix = "url.ignore")
@Data
public class UrlIgnoreConfig {
    /**
     * 需要忽略的 URL 格式，不考虑请求方法
     */
    private List<String> pattern = new ArrayList<>();

    /**
     * 需要忽略的 GET 请求
     */
    private List<String> get = new ArrayList<>();

    /**
     * 需要忽略的 POST 请求
     */
    private List<String> post = new ArrayList<>();

    /**
     * 需要忽略的 DELETE 请求
     */
    private List<String> delete = new ArrayList<>();

    /**
     * 需要忽略的 PUT 请求
     */
    private List<String> put = new ArrayList<>();

}
