package com.coolron.dateapi;

import java.time.LocalDateTime;

/**
 * csdn 大佬链接地址：
 *
 * https://blog.csdn.net/qq_29411737/article/details/80835658#t9
 *
 * @Auther: xf
 * @Date: 2019/1/26 15:35
 * @Description: LocalDateTime为日期时间处理类
 *
 * now 相关的方法可以获取当前日期或时间，
 * of 方法可以创建对应的日期或时间，
 * parse方法可以解析日期或时间，
 * get方法可以获取日期或时间信息，
 * with方法可以设置日期或时间信息，
 * plus或minus方法可以增减日期或时间信息
 *
 */
public class LocalDateTimeTest {

    public static void main(String[] args) {
//        LocalDateTimeTest localDateTime = new LocalDateTimeTest();

//        String fillTempID0 = "123|abc".split("\\|")[0];
//        String fillTempID1 = "123|abc".split("\\|")[1];
//        System.out.println(fillTempID0);
//        localDateTime.nowTest();
//        localDateTime.ofTest();
//        localDateTime.instantTest();

        int total = 0;
        int a = 1;
        total = total + a++;
        System.out.println(total); // 1
    }

    private void nowTest(){
        // 从默认时区的系统时钟获取当前的日期时间。不用考虑时区差
        LocalDateTime date = LocalDateTime.now();
        //2018-07-15T14:22:39.759
        System.out.println(date);

        System.out.println("year: " + date.getYear());
        System.out.println("month: " + date.getMonthValue());
        System.out.println("day: " + date.getDayOfMonth());
        System.out.println("hour: " + date.getHour());
        System.out.println("minute: " + date.getMinute());
        System.out.println("second: " + date.getSecond());
    }

    /**
     * of：手动创建日期
     * plus/minus 加减操作  链式调用
     */
    private void ofTest(){
        // 手动创建一个LocalDateTime实例
        LocalDateTime date = LocalDateTime.of(1993, 8, 4, 9, 31, 31, 31);
        System.out.println(date);
        // plus加操作，得到新的日期实例
        LocalDateTime datePlus = date.plusDays(12).plusYears(1).plusMonths(4);
        System.out.println(datePlus);

        // minus减操作，得到新的日期实例
        LocalDateTime dateMinus = date.minusYears(2).minusDays(4);
        System.out.println(dateMinus);
    }

    private void parseTest(){
        LocalDateTime date = LocalDateTime.parse("2017-02-03T12:30:30");
        System.out.println(date.getNano());
    }

}
