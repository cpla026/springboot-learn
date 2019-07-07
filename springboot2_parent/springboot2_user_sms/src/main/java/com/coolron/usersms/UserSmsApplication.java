package com.coolron.usersms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: xf
 * @Date: 2018/12/18 20:24
 * @Description: 用户服务
 */
@MapperScan("com.coolron.usersms.dao")
@SpringBootApplication
public class UserSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserSmsApplication.class);
    }
}
