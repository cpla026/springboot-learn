package com.coolron.lambda;

import java.util.Optional;

/**
 * @Auther: xf
 * @Date: 2019/1/10 10:28
 * @Description: Optional 接口
 *
 * Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，
 * Optional 被定义为一个简单的容器，其值可能是null或者不是null。
 * 在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional。
 */
public class T5 {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }
}
