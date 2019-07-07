package com.coolron.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2019/1/3 22:46
 * @Description: 分列式消费者 C
 */
@Component
@RabbitListener(queues = "fanout.c")
public class FanoutConsumerC {

    @RabbitHandler
    public void showMessage(String message){
        System.out.println(">>>FanoutConsumerC >>>fanout.c>>>接收到消息："+message);
    }

}
