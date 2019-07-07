package com.coolron.ron.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @Auther: xf
 * @Date: 2018/10/13 16:58
 * @Description: 异步处理控制器
 */
@Slf4j
@RestController
public class AsyncController {

    // 同步处理
    @RequestMapping("/order1")
    public String order1() throws Exception {

        log.info("主线程开始");
        Thread.sleep(1000);

        log.info("主线程结束");

        return "success";
    }

    /**
     *
     * @return
     * @throws Exception
     *
     * Callable 实际上就是开启来了一个副线程
     *
     * 日志顺序:
     * 主线程立马返回
     * com.coolron.ron.controller.AsyncController - 主线程开始
     * com.coolron.ron.controller.AsyncController - 主线程结束
     *
     * 副线程执行了一秒
     * com.coolron.ron.controller.AsyncController - 副线程开始
     * com.coolron.ron.controller.AsyncController - 副线程结束
     *
     */
    @RequestMapping("/order2")
    public Callable<String> order2() throws Exception {

        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程结束");
                return "success";
            }
        };

        log.info("主线程结束");
        return result;
    }
}
