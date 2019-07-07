package com.coolron.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2019/1/3 22:46
 * @Description: 分列式消费者 B
 */
@Component
@RabbitListener(queues = "fanout.b")
public class FanoutConsumerB {

    @RabbitHandler
    public void showMessage(String message){
        System.out.println(">>>FanoutConsumerB >>>fanout.b>>>接收到消息："+message);
    }

}
