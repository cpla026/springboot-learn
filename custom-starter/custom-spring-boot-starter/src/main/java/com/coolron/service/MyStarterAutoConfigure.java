package com.coolron.service;/**
 * Created by Administrator on 2019/8/25.
 */

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: xf
 * @Date: 2019/8/25 15:25
 * @Description: starter 自动配置类
 */
@Configuration
@EnableConfigurationProperties(MyStarterProperties.class)
public class MyStarterAutoConfigure {

    @Bean
    // 当Spring Context中不存在该Bean时,将该bean添加到spring容器中
    @ConditionalOnMissingBean
    // 当配置文件中com.coolron.enabled=true时
    @ConditionalOnProperty(prefix = "com.coolron", value = "enabled", havingValue = "true")
    public MyStarterService myStarterService(MyStarterProperties starterProperties){
        return new MyStarterService(starterProperties);
    }
}
