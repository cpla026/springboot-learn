package com.coolron.mq.customer.test;

import com.coolron.mq.MqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: xf
 * @Date: 2018/12/11 10:46
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=MqApplication.class)
public class ProduceTest {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 直接模式  最简单的使用
     * 一个消息只会有一个消费者，
     */
    @Test
    public void testSend(){

        // 多个消费者只有一个消费者接收到消息
        rabbitTemplate.convertAndSend("hello","直接模式测试");

        // 多个接收者  均匀的分布到消费者中
        /*for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("hello","直接模式测试 ：" + i);
        }*/
    }

    /**
     * 分列模式 -- 将消息一次发给多个队列
     * 不需要 routingKey 规则匹配  直接模式的加强版
     */
    @Test
    public void testSendFanout(){
        rabbitTemplate.convertAndSend("fanoutExchange","", "分列模式发送的消息");
    }

    /**
     * 主题模式
     */
    @Test
    public void testSendTopic1(){
        // 根据TopicConfig 中 RoutingKey 的配置 ，队列 topic.a、b、c 都可以匹配到
        rabbitTemplate.convertAndSend("topicExchange","topic.coolron","主题模式");
    }

    @Test
    public void testSendTopic2(){
        // 根据TopicConfig 中 RoutingKey 的配置 ，队列 topic.b 可以匹配到 topic.#
        rabbitTemplate.convertAndSend("topicExchange","topic.cool.ron","主题模式");
    }

    @Test
    public void testSendTopic3(){
        // 根据TopicConfig 中 RoutingKey 的配置 ，队列 topic.b、c 可以匹配到 topic.# topic.*
        rabbitTemplate.convertAndSend("topicExchange","topic.ron","主题模式");
    }

    @Test
    public void testSendTopic4(){
        // 根据TopicConfig 中 RoutingKey 的配置 ，队列 topic.b、c 可以匹配到 topic.# topic.*，“” 代表一个词
        rabbitTemplate.convertAndSend("topicExchange","topic.","主题模式");
    }

    @Test
    public void testSendTopic5(){
        // 根据TopicConfig 中 RoutingKey 的配置 ，队列 topic.b 可以匹配到 topic.#
        rabbitTemplate.convertAndSend("topicExchange","topic","主题模式");
    }

}
