package com.zhumingbei.babybei_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhumingbei.babybei_server.mapper")
public class BabybeiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabybeiServerApplication.class, args);
    }

}
