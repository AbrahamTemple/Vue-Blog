package com.cloud.mdblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.cloud.mdblog.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class MdBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdBlogApplication.class, args);
    }

}
