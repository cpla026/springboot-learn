package com.coolron.dateapi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Auther: xf
 * @Date: 2019/1/26 16:37
 * @Description: Instant
 *
 * 表示时间线上的一个点
 * 参考点是标准的Java纪元(epoch)，即1970-01-01T00：00：00Z（1970年1月1日00:00 GMT）
 *
 */
public class InstantTest {

    public static void main(String[] args) {

        Instant start = Instant.now();

        // 自纪元以来经过的秒数
        long epochSecond = start.getEpochSecond();
        System.out.println(epochSecond);

        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(System.currentTimeMillis());

        System.out.println(start.toEpochMilli());
        System.out.println(start.atOffset(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());

        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());

    }

}
