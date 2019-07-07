package com.coolron.dateapi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Auther: xf
 * @Date: 2019/4/24 11:18
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
        String goalTime = LocalDateTime.parse("2019-04-25 12:12:12", df).getYear() + "-01-01 00:00:00.000";


        System.out.println(goalTime);
    }

}
