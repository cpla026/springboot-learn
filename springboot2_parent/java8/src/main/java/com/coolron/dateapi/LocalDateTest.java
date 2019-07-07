package com.coolron.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @Auther: xf
 * @Date: 2019/1/26 15:34
 * @Description:  LocalDate为日期处理类
 */
public class LocalDateTest {

    public static void main(String[] args) {
        // yyyy-MM-dd    2019-02-02
        LocalDate now = LocalDate.now();

        // 创建日期
        LocalDate today = LocalDate.of(2019, 2, 2);

        // 默认格式yyyy-MM-dd，03不能写成2
        LocalDate tomorrow = LocalDate.parse("2019-02-03");

        // 如果不是闰年 传入29号报错:java.time.DateTimeException: Invalid date 'February 29' as '2018' is not a leap year
        LocalDate.parse("2000-02-28");

        localDateTransferTest();

    }

    /**
     * 日期转换常用,第一天或者最后一天...
     */
    public static void localDateTransferTest(){
        // 现在时间：2019-02-02
        LocalDate today = LocalDate.now();
        // 取本月第1天： 2019-02-01
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月第2天：2019-02-02
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        // 取本月最后一天， 2019-02-28
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // 取下一个月第一天：2019-03-01
        LocalDate lastDay = lastDayOfThisMonth.plusDays(1);
        // 2019年3月第一个周三   2018-03-06
        LocalDate thirdMonday = LocalDate.parse("2019-03-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));

        System.out.println("");
    }

}
