package com.coolron.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Auther: xf
 * @Date: 2019/1/3 21:25
 * @Description: 分列模式
 */
@Configuration
public class FanoutConfig {

    /*********************** 定义队列 start ************************/
    @Bean
    public Queue fanoutA() {
        return new Queue("fanout.a");
    }

    @Bean
    public Queue fanoutB() {
        return new Queue("fanout.b");
    }

    @Bean
    public Queue fanoutC() {
        return new Queue("fanout.c");
    }
    /*********************** 定义队列 end ************************/

    /**
     * 分列式 交换器
     * 名称：fanoutExchange
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /****************** 队列绑定交换机 start ********************/

    // fanoutA 与 fanoutExchange 绑定
    @Bean
    public Binding bindingExchangeWithA() {
        return BindingBuilder.bind(fanoutA()).to(fanoutExchange());
    }

    // fanoutB 与 fanoutExchange 绑定
    @Bean
    public Binding bindingExchangeWithB() {
        return BindingBuilder.bind(fanoutB()).to(fanoutExchange());
    }

    // fanoutC 与f anoutExchange 绑定
    @Bean
    public Binding bindingExchangeWithC() {
        return BindingBuilder.bind(fanoutC()).to(fanoutExchange());
    }

    /****************** 队列绑定交换机 end ********************/

}
