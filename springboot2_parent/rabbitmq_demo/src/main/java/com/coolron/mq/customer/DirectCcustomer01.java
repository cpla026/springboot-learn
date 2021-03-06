package com.coolron.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2019/1/3 20:53
 * @Description: 直接模式 消费者 1
 */
@Component
@RabbitListener(queues = "hello")
public class DirectCcustomer01 {
    @RabbitHandler
    public void showMessage(String message){
        System.out.println("DirectCcustomer01 >>>hello>>>接收到消息："+message);
    }
}
