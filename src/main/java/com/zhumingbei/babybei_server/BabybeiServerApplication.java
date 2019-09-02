package com.zhumingbei.babybei_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.zhumingbei.babybei_server.mapper")
@EnableScheduling
@EnableCaching
public class BabybeiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabybeiServerApplication.class, args);
    }

}
