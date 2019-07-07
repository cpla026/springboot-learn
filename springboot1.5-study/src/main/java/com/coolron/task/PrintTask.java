package com.coolron.task;

import com.alibaba.fastjson.JSON;
import com.coolron.book.domain.BookDetail;
import com.coolron.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Scheduled 创建定时任务
 * @scheduled 注解用来配置到方法上来完成对应的定时任务的配置，如执行时间，间隔时间，延迟时间等等
 */
// @Compoment用来标明这是一个被Spring管理的Bean
@Component
public class PrintTask {

    @Autowired
    private BookService bookService;

    /**
     *
     * cron属性:
     *    时间表达式  完成各种时间的配置，我们通过CRON表达式几乎可以完成任意的时间搭配，它包含了六或七个域
     *    "0 0 12 * * ?"    每天中午十二点触发    "0 15 10 ? * *"    每天早上10：15触发
     *
     * fixedRate属性:
     *    时间间隔   上一个调用开始后再次调用的延时（不用等待上一次调用完成）
     *    这样就会存在重复执行的问题，所以不是建议使用，但数据量如果不大时在配置的间隔时间内可以执行完也是可以使用的。
     *
     * fixedDelay属性:
     *    时间间隔   该属性的功效与上面的fixedRate则是相反的，配置了该属性后会等到方法执行完成后延迟配置的时间再次执行该方法
     *
     * initialDelay属性:
     *    该属性跟上面的fixedDelay、fixedRate有着密切的关系,
     *    该属性的作用是第一次执行延迟时间，只是做延迟的设定，并不会控制其他逻辑，所以要配合fixedDelay或者fixedRate来使用
     *
     */
    //@Scheduled(cron = "30 * * * * *") // 每小时的10分执行该方法
    //@Scheduled(fixedRate = 1000 * 1)  // 1秒  (不用等待上一次调用完成)
    //@Scheduled(fixedDelay = 1000 * 1)   // 1秒  (等到方法执行完成后延迟配置的时间执行下一次)
   // @Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000 * 3)  // 等待了10秒钟后  每1秒执行一次
    public void cron() throws Exception
    {
        BookDetail book = bookService.getDetail(12);
        System.out.println(JSON.toJSONString(book));
        System.out.println("执行测试cron时间："+ new Date(System.currentTimeMillis()));
    }


    /**
     * 是上一个调用开始后再次调用的延时（不用等待上一次调用完成）
     */
    //@Scheduled(fixedRate = 1000 * 1)
    public void fixedRate() throws Exception
    {
        Thread.sleep(2000);
        System.out.println("执行测试fixedRate时间："+ new Date(System.currentTimeMillis()));
    }

    /**
     * 上一个调用完成后再次调用的延时调用
     */
    //@Scheduled(fixedDelay = 1000 * 1)
    public void fixedDelay() throws Exception
    {
        Thread.sleep(3000);
        System.out.println("执行测试fixedDelay时间："+ new Date(System.currentTimeMillis()));
    }

    /**
     * 第一次被调用前的延时，单位毫秒
     */
    //@Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000 * 2)
    public void initialDelay() throws Exception
    {
        System.out.println("执行测试initialDelay时间："+ new Date(System.currentTimeMillis()));
    }

}
