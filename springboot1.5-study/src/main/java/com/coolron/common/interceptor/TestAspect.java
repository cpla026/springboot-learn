package com.coolron.common.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Date;

/**
 * @Auther: xf
 * @Date: 2018/10/13 16:10
 * @Description:  切片测试
 *
 * 注意 Filter Interceptor aspect 的对比
 */
//@Aspect
// 注入到spring 容器中
//@Component
public class TestAspect {

    // @Before()  相当于 Inerceptor 的 preHandle()
    // @After() 相当于 Inerceptor 的 postHandle()
    // @AfterThrowing   方法抛出某些异常时调用

    // @Around 包含了前面三种  前面三种的逻辑都可以在 @Around 中实现
    // @Around 实际上定义的是在上面时候起作用
    // execution 定义的是在哪些方法上起作用
    // ProceedingJoinPoint 类 包含了当前拦截的方法的一些信息
    @Around("execution(* com.coolron.*.controller.*.*(..))")  // 切入点
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");
        // 获取controller 方法的参数
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }

        long startTime = new Date().getTime();
        // 调用被拦截的那个方法  最后返回就可以了  类似于 Filter 的 doFilter() 方法
        Object obj = pjp.proceed();
        System.out.println("time aspect 耗时: " + (new Date().getTime() - startTime));
        System.out.println("time aspect end");

        return obj;
    }

}
