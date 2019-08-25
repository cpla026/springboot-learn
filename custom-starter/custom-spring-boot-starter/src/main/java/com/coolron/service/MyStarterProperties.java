package com.coolron.service;/**
 * Created by Administrator on 2019/8/25.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: xf
 * @Date: 2019/8/25 15:04
 * @Description: 配置文件映射实体类
 * 将配置文件中 com.coolron.name 的属性值映射到该实体中
 */
@ConfigurationProperties(prefix = "com.coolron")
public class MyStarterProperties {
    private String name = "ron";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
