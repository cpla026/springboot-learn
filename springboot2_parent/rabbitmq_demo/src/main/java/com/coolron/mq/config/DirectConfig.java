package com.coolron.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: xf
 * @Date: 2019/1/3 21:18
 * @Description: 直接模式消息队列
 */
@Configuration
public class DirectConfig {
    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }
}
