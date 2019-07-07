package com.coolron.kafka.producer.config;/**
 * Created by Administrator on 2019/6/15.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @Auther: xf
 * @Date: 2019/6/15 22:18
 * @Description: 定时发送消息
 */
@Component
@EnableScheduling
public class KafkaMessageProducer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageProducer.class);

    // SpringKafka提供了使用Producer的KafkaTemplate类发送消息，并提供将数据发送到Kafka主题的高级操作 提供异步和同步方法，异步方法返回Future。
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.app.topic.foo}")
    private String topic;

    //@Scheduled(cron = "00/5 * * * * ?")
    public void send() {
        String message = "Hello World---" + System.currentTimeMillis();
        LOG.info(">>>>>>>topic=" + topic + ",message=" + message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(success -> LOG.info("KafkaMessageProducer 发送消息成功！"),
                fail -> LOG.error("KafkaMessageProducer 发送消息失败！"));
    }
}
