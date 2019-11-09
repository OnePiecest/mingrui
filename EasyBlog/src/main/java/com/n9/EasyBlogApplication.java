package com.n9;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.n9.dao")
@SpringBootApplication
public class EasyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyBlogApplication.class, args);
    }
}
