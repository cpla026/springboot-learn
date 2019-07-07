package com.coolron.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: xf
 * @Date: 2018/12/18 20:24
 * @Description: 用户服务
 */
@MapperScan("com.coolron.mybatis.dao")
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class);
    }
}
