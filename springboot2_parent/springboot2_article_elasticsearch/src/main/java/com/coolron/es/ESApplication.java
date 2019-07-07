package com.coolron.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Auther: xf
 * @Date: 2018/12/18 20:24
 * @Description:
 */
@SpringBootApplication
public class ESApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class);
    }
}
