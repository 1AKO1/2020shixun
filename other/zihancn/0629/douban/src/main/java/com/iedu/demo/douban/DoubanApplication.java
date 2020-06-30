package com.iedu.demo.douban;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
@MapperScan("com.iedu.demo.douban.dao")
public class DoubanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoubanApplication.class, args);
    }

}
