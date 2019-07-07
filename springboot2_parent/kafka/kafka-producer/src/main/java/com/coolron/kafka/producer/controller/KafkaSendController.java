package com.coolron.kafka.producer.controller;/**
 * Created by Administrator on 2019/6/15.
 */

import com.coolron.kafka.producer.service.KafkaMessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xf
 * @Date: 2019/6/15 22:42
 * @Description: 请求发送消息
 */
@RestController
@RequestMapping(value="send",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
public class KafkaSendController {

    @Autowired
    private KafkaMessageSendService kafkaMessageSendService;

    @RequestMapping(value="/sendMessage",method= RequestMethod.POST)
    public String send(@RequestParam(required=true) String message){
        try {
            kafkaMessageSendService.send(message);
        } catch (Exception e) {
            return "send failed.";
        }
        return message;
    }

}
