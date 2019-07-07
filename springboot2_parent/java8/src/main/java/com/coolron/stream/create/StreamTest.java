package com.coolron.stream.create;

/**
 * @Auther: xf
 * @Date: 2019/1/25 10:01
 * @Description:
 *
 * stream 顺序流
 *
 * 顺序流操作是单线程执
 *
 * #### 使用Stream静态方法来创建Stream ####
 * 1. of方法：有两个overload方法，一个接受变长参数，一个接口单一值
 *
 */
public class StreamTest {

    public static void main(String[] args) {

        /************* 使用Stream静态方法来创建Stream ****************/

        // 1. of方法：有两个overload方法，一个接受变长参数，一个接口单一值
//        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
//        Stream<String> stringStream = Stream.of("hello ron");

        // 2. generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，
        // 每次调用返回一个给定类型的对象）
        // 这个无限长度Stream是懒加载，一般这种无限长度的Stream都会配合Stream的limit()方法来用。
//        Stream.generate(new Supplier<Double>() {
//            @Override
//            public Double get() {
//                return Math.random();
//            }
//        });
//
//        Stream.generate(() -> Math.random());
//        Stream.generate(Math::random);

        /***************** 通过Collection子类获取Stream *****************/

    // Collection接口有一个stream方法，所以其所有子类都都可以获取对应的Stream对象。
    // 源码：
    /*public interface Collection<E> extends Iterable<E> {
        //其他方法省略
        default Stream<E> stream() {
            return StreamSupport.stream(spliterator(), false);
        }
    }*/
        // 本例中 List集合继承了 Collection  通过 list.stream() 即可获取stream

    }

}
