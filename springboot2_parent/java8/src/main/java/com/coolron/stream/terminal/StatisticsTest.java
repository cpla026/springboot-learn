package com.coolron.stream.terminal;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2019/1/24 16:02
 * @Description: 产生统计结果的收集器
 *
 * 主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果
 */
public class StatisticsTest {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }

}
