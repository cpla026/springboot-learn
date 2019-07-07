package com.coolron.kafka.consumer.config;/**
 * Created by Administrator on 2019/6/15.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2019/6/15 22:58
 * @Description:
 */
@Component
public class KafkaMessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics={"${kafka.app.topic.foo}"})
    public void receive(@Payload String message, @Headers MessageHeaders headers){
        LOG.info(">>>>>>>>>KafkaMessageConsumer 接收到消息：" + message);
        headers.keySet().forEach(key->LOG.info("{}: {}",key,headers.get(key)));
    }

}
