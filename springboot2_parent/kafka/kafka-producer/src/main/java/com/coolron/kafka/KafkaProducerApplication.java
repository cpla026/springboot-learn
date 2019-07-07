package com.coolron.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: xf
 * @Date: 2018/12/18 20:24
 * @Description:  消息生产者
 *
 * 参考：https://www.cnblogs.com/guanzhyan/p/8979050.html
 */
@SpringBootApplication
public class KafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class);
    }
}
