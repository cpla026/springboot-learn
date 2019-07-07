package com.coolron.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2019/1/3 22:49
 * @Description: 分列式消费者 A
 */
@Component
@RabbitListener(queues = "fanout.a")
public class FanoutConsumerA {
    @RabbitHandler
    public void showMessage(String message){
        System.out.println(">>>FanoutConsumerA >>>fanout.a>>>接收到消息："+message);
    }
}
