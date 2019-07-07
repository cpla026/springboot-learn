package com.coolron.mq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2019/1/3 22:46
 * @Description: 主题模式 消费者 A
 */
@Component
@RabbitListener(queues = "topic.b")
public class TopicCustomerB {

    @RabbitHandler
    public void showMessage(String message){
        System.out.println("TopicCustomerB>>>topic.b>>>接收到消息："+message);
    }

}
