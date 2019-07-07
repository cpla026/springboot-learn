package com.coolron.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: xf
 * @Date: 2019/1/3 18:46
 * @Description: 主题模式
 *
 * 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上
 * RouteKey：匹配规则，使每个消息队列只关心自己匹配的消息
 */
@Configuration
public class TopicConfig {

    /*********************** 定义队列 start ************************/
    @Bean
    public Queue topiocA() {
        return new Queue("topic.a");
    }

    @Bean
    public Queue topicB() {
        return new Queue("topic.b");
    }

    @Bean
    public Queue topicC() {
        return new Queue("topic.c");
    }
    /*********************** 定义队列 end ************************/


    /**
     * 主题模式 交换器
     * 名称：topicExchange
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /****************** 队列绑定交换机 start ********************/

    /**
     * topicA 与 topicExchange 绑定
     * routingKey : topic.coolron
     * 只会接收包含topic.msg的消息
     */
    @Bean
    public Binding bindingTopicExchangeWithA() {
        return BindingBuilder.bind(topiocA()).to(topicExchange()).with("topic.coolron");
    }

    /**
     * topicB 与 topicExchange 绑定
     * routingKey : topic.#
     * 只会接收 topic 开头的信息 topic、topic.AA、topic.AA.BB、topic.AA.BB.CC
     * # 匹配0个、一个或多个词
     */
    @Bean
    public Binding bindingTopicExchangeWithB() {
        return BindingBuilder.bind(topicB()).to(topicExchange()).with("topic.#");
    }

    /**
     * topicC 与 topicExchange 绑定
     * routingKey : topic.*
     * 只能接收类似 topic.AA、topic.BB、topic.CC 的消息
     * * 匹配一个词
     */
    @Bean
    public Binding bindingTopicExchangeWithC() {
        return BindingBuilder.bind(topicC()).to(topicExchange()).with("topic.*");
    }
    /****************** 队列绑定交换机 end ********************/

}
