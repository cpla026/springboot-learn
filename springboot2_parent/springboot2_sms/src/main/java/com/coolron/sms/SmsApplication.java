package com.coolron.sms;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: xf
 * @Date: 2019/1/5 18:09
 * @Description: 短信服务
 */
@SpringBootApplication
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class);
    }
    /**
     * 短信队列
     */
    @Bean
    public Queue smsQueue(){
        return new Queue("sms");
    }
}
