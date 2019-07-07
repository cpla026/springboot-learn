package com.coolron.dateapi;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * @Auther: xf
 * @Date: 2019/1/28 10:24
 * @Description: 它与Period类似，不同之处在于Duration的时间分量为纳秒精度，并考虑了ZonedDateTime实例之间的时区
 *
 *  Duration: 计算两个时间之间的间隔
 *  Period：  计算两个日期之间的间隔
 *
 */
public class DurationAndPeriodTest {

    public static void main(String[] args) {
        DurationAndPeriodTest durationTest = new DurationAndPeriodTest();
        durationTest.durationTest();
        durationTest.periodTest();
    }

    private void durationTest(){
        LocalDateTime dateTimeA = LocalDateTime.of(2019, 2, 2, 8, 30, 0, 0);
        LocalDateTime dateTimeB = LocalDateTime.of(2019, 2, 2, 9, 45, 0, 0);

        // 通过调用静态方法between或of来创建Duration
        Duration duration = Duration.between(dateTimeA, dateTimeB);

        // There are 1 hours and 15 minutes.
        System.out.printf("There are %d hours and %d minutes.%n", duration.toHours(), duration.toMinutes() % 60);
    }

    private void periodTest(){
        LocalDate localDate =LocalDate.now();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        LocalDate localDate2 = LocalDate.of(2019,8,4);
        Period pe = Period.between(localDate, localDate2);
        System.out.println(pe.getDays());   // 2
        System.out.println(pe.getMonths()); // 6
        System.out.println(pe.getYears());  // 0
    }

}
