package com.coolron.dateapi;

import java.time.LocalTime;

/**
 * @Auther: xf
 * @Date: 2019/1/26 15:35
 * @Description: LocalTime为时间处理类
 */
public class LocalTimeTest {

    public static void main(String[] args) {
        // 10:37:02.558
        LocalTime todayTimeWithMillisTime = LocalTime.now();
        // 10:37:02
        LocalTime todayTimeWithNoMillisTime = LocalTime.now().withNano(0);
        // 10:37:02
        LocalTime praseTime = LocalTime.parse("10:37:02");
    }
}
