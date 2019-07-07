package com.coolron.webmvcconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: xf
 * @Date: 2018/12/18 20:24
 * @Description: 自定义 webMVC 配置
 */
@MapperScan("com.coolron.webmvcconfig.user.dao")
@SpringBootApplication
public class WebMvcConfigUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMvcConfigUserApplication.class);
    }
}
