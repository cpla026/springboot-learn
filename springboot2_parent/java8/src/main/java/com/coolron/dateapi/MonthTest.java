package com.coolron.dateapi;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @Auther: xf
 * @Date: 2019/1/29 09:54
 * @Description:
 */
public class MonthTest {

    public static void main(String[] args) {
        System.out.println(Month.DECEMBER);         // DECEMBER
        System.out.println(Month.of(2));            // FEBRUARY

        Month month = Month.FEBRUARY;
        System.out.println(month.getValue());       // 2
        System.out.println(month.minus(3));         // NOVEMBER
        System.out.println(month.plus(2));          // APRIL
        System.out.println(month.length(false));    // 28
        System.out.println(month.length(true));     // 29
    }

    private static void test(){
        Month month = Month.APRIL;

        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.getDefault()));      // 四月
        System.out.println(month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));     // 四月
        System.out.println(month.getDisplayName(TextStyle.NARROW, Locale.getDefault()));    // 4

        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH));           // April
        System.out.println(month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));          // Apr
        System.out.println(month.getDisplayName(TextStyle.NARROW, Locale.ENGLISH));         // A
    }

}
