package com.coolron.usersms.service;

import com.coolron.usersms.dao.UserMapper;
import com.coolron.usersms.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/12/18 17:35
 * @Description:
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<User> list() {
        List<User> list = userMapper.list();
        return list;
    }

    public User getInfo(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void sendSms(String mobile) {
        /**
         * 1.生成6位短信验证码
         * 阿帕奇 提供的工具类
         */
        String code = RandomStringUtils.randomNumeric(6);
        log.info(mobile + "收到验证码是：" + code);
        /**
         * 2.将验证码放入redis
         * 此处将code 放入redis仅仅是为了用户输入验证码后，系统后台取出来检验
         */
        redisTemplate.opsForValue().set("smscode_" + mobile, code+"" ,5, TimeUnit.MINUTES );//五分钟过期
        //3.将验证码和手机号发动到rabbitMQ中
        Map<String,String> map=new HashMap();
        map.put("mobile", mobile);
        map.put("code", code);
        rabbitTemplate.convertAndSend("sms",map);
    }
}
