package com.coolron.dateapi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: xf
 * @Date: 2019/1/26 15:40
 * @Description: DateTimeFormatter 日期时间格式化器
 *
 * 配合LocalDate/LocalTime/LocalDateTime使用，
 * 比如：把当前日期格式化成yyyy-MM-dd hh:mm:ss的形式
 *
 */
public class DateTimeFormatterTest {

    public static void main(String[] args) {

        // 自定义格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

        String strDate1 = ldt.format(formatter);
        String strDate = formatter.format(ldt);

        System.out.println(strDate1);
        System.out.println(strDate);

        // 使用api提供的格式
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt2 = LocalDateTime.now();
        String strDate3 = dtf.format(ldt2);
        System.out.println(strDate3);

        // 解析字符串to时间
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        LocalDateTime ldt4 = LocalDateTime.parse("2019-02-02 10:40:05",df);
        System.out.println("LocalDateTime转成String类型的时间："+localTime);
        System.out.println("String类型的时间转成LocalDateTime："+ldt4);
    }

}
