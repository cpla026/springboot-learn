package com.coolron.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Auther: xf
 * @Date: 2019/1/24 10:56
 * @Description:
 *
 * stream 使用步骤：
 * 1、创建流  : 将结合转换成流
 * 2、中间操作：对数据源的数据进行处理，多个中间操作组合成一个链，遇到终止操作之前不会作任何处理。惰性加载
 * 3、终止操作：执行中间操作链，产生结果。
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList(Arrays.asList(1,null,3,4,null,6));
        /**
         * 获取list中不为空的个数
         * stream(): 创建。获取Stream实例  包含所有的nums
         * filter()：转换。把一个Stream转换成另外一个Stream  包含过滤后的nums
         * count()： 聚合。把Stream的里面包含的内容按照某种算法来汇聚成一个值
         */
        //long count = nums.stream().filter(num -> num != null).count();
        //System.out.println(count);

        List<Integer> collect = nums.stream().filter(num -> num != null).collect(toList());

        /************* 使用Stream静态方法来创建Stream ****************/

        // 1. of方法：有两个overload方法，一个接受变长参数，一个接口单一值
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        Stream<String> stringStream = Stream.of("hello ron");

        // 2. generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，
        // 每次调用返回一个给定类型的对象）
        // 这个无限长度Stream是懒加载，一般这种无限长度的Stream都会配合Stream的limit()方法来用。
        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });

        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);


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


        /************** 转换Stream  ******************/
        // 转换Stream其实就是把一个Stream通过某些行为转换成一个新的Stream

        // 1. distinct: 对于Stream中包含的元素进行去重操作（去重逻辑依赖元素的equals方法），新生成的Stream中没有重复的元素
        // 2. filter: 对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素
        // 3. map: 对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
        //    map 的变种方法 mapToInt，mapToLong和mapToDouble：mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型
        // 4. flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        // flatMap 把 inputStream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。

        // 5. peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数；
        // 6. limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
        // 7. skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
        // 8. sorted()——自然排序。 sorted(Comparator com)——定制排序。

        List<Integer> nums2 = new ArrayList(Arrays.asList(1,1,null,4,2,3,null,5,6,7,8,9,10));
        System.out.println("sum is:"+nums2.stream().filter(num -> num != null).distinct().sorted().mapToInt(num -> num * 2).peek((e) -> System.out.println(e)).skip(2).limit(4).sum());
        /**
         * 仅仅是为了理解，多个转换操作只会在汇聚操作的时候融合起来，一次循环完成
         *
         * 原集合：1,1,null,4,2,3,null,5,6,7,8,9,10
         * filter：1,1,4,2,3,5,6,7,8,9,10
         * distinct：1,4,2,3,5,6,7,8,9,10
         * sorted: 1,2,3,4,5,6,7,8,9,10
         * mapToInt：2,4,6,8,10,12,14,16,18,20
         * peek：新的stream每调用一次执行一次指定的函数（consumer）
         * skip：6,8,10,12,14,16,18,20 跳过前两个，打印 2,4
         * limit：6,8,10,12 截断前四个，打印 6,8,10,12
         * sum: 36 对最新的stream求和
         */

    }
}
