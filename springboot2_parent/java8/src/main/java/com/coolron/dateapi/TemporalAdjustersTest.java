package com.coolron.dateapi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @Auther: xf
 * @Date: 2019/1/26 15:39
 * @Description: TemporalAdjusters 时间调节器
 *
 * 得到当月的第一天、最后一天，当年的第一天、最后一天，下一周或前一周的某天等。
 *
 */
public class TemporalAdjustersTest {

    public static void main(String[] args) {

        // 例如获取下周日  下一个工作日
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        // 获取一年中的第一天
        LocalDateTime ldt2 = ldt1.withDayOfYear(1);
        System.out.println(ldt2);
        // 获取一个月中的第一天
        LocalDateTime ldt3 = ldt1.withDayOfMonth(1);
        System.out.println(ldt3);

        // 下一个周一
        LocalDateTime ldt4 = ldt1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(ldt4);

        // 获取下一个工作日
        LocalDateTime ldt5 = ldt1.with((t) -> {
            LocalDateTime ldt6 = (LocalDateTime)t;
            DayOfWeek dayOfWeek = ldt6.getDayOfWeek();
            if (DayOfWeek.FRIDAY.equals(dayOfWeek)){
                return ldt6.plusDays(3);
            }
            else if (DayOfWeek.SATURDAY.equals(dayOfWeek)){
                return ldt6.plusDays(2);
            }
            else {
                return ldt6.plusDays(1);
            }
        });
        System.out.println(ldt5);

    }

}
