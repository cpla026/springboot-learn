package com.coolron.lambda.funinterface;

/**
 * @Auther: xf
 * @Date: 2019/2/1 17:00
 * @Description:
 */
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

public final class MyCollectors {

    public static <T, K, V> Collector<T, ?, Map<K, V>> toMap(Function<T, K> f1, Function<T, V> f2) {
        return new ForceToMapCollector<T, K, V>(f1, f2);
    }

}