package com.coolron.user.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: xf
 * @Date: 2018/11/14 14:51
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTests {

    @Test
    public void test(){
        User user = new User();
        user.setName("ron");
        log.info(user.getName());
    }
}
