package com.coolron.sms.mq;


import com.aliyuncs.exceptions.ClientException;
import com.coolron.sms.util.SmsUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信监听类
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Value("${aliyun.sms.template_code}")
    private String template_code;//模板编号

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;//签名

    @RabbitHandler
    public void sendSms(Map<String,String> map){
        System.out.println("手机号："+map.get("mobile"));
        System.out.println("验证码："+map.get("code"));
        try {
            // "{\"name\":\" Ron \", \"code\":\"930804\"}"  模板
            //smsUtil.sendSms(map.get("mobile"),template_code,sign_name,"{\"name\":\"ron\", + map.get("code") +"\"}");
            // 模板格式根据自己的需求来做
            String param = "{\"name\":\"Ron\", \"code\":\"" + map.get("code") + "\"}";
            smsUtils.sendSms(map.get("mobile"),sign_name,template_code, param);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
