package com.coolron.stream.terminal;

import java.util.Random;

/**
 * @Auther: xf
 * @Date: 2019/1/24 15:49
 * @Description:
 *
 * forEach 终止操作 返回 void
 *
 * 接收consumer接口参数  中间操作之后为每一个流元素执行consumer
 * 迭代流中的每个数据
 */
public class ForEach {

    public static void main(String[] args) {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}
