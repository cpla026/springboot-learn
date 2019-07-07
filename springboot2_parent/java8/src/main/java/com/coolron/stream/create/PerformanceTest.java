package com.coolron.stream.create;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2019/1/25 10:11
 * @Description:
 *  并行流和顺序流性能测试
 */
public class PerformanceTest {

    public static void main(String[] args) {

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        /***************** 顺序、并行流测试 *****************/
        long t0 = System.nanoTime();

        //long count = values.stream().sorted().count();
        long count = values.parallelStream().sorted().count();

        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        // sequential912 ms  // parallel 437 ms
        System.out.println(String.format("sequential sort took: %d ms", millis));

    }
}
