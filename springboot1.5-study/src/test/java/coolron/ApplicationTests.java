package com.coolron;

import com.coolron.ron.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: xf
 * @Date: 2018/11/21 16:30
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    /**
     * RedisAutoConfiguration 中注入 ：
     * redisTemplate  ： k - v 操作对象的
     * stringRedisTemplate ： k - v 操作字符串的
     */
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * stringRedisTemplate.opsForValue() 操作String
     * stringRedisTemplate.opsForList()  操作list 列表
     * stringRedisTemplate.opsForSet()   操作set集合
     * stringRedisTemplate.opsForHash()  操作hash散列
     * stringRedisTemplate.opsForZSet()  操作有序集合
     *
     * 后面直接跟上 redis的命令即可。
     */
    @Test
    public void test01(){
        // 操作String
        stringRedisTemplate.opsForValue().append("str1","hello");
        String str1 = stringRedisTemplate.opsForValue().get("str1");
        // >>>>>>>>>>>>>>>>>>str: hellohello  原因： 执行了两次 追加。
        log.info(">>>>>>>>>>>>>>>>>>str: {}",str1);

        // 操作list 列表
        stringRedisTemplate.opsForList().leftPush("list1","广东省");
        stringRedisTemplate.opsForList().leftPush("list1","22222");
    }

    /**
     * 测试保存对象  使用 redisTemplate
     */
    @Test
    public void test02(){
        User user = new User();
        user.setId(1);
        user.setName("ron");
        // 保存对象，默认使用 JDK 序列化机制，序列化后的数据保存到redis中乱码
        // 解决：
        // 1、将对象转为 json
        // 2、改变 redisTemplate 默认的序列化规则
        redisTemplate.opsForValue().set("person",user);

    }
}
