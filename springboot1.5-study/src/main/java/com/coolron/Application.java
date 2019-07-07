package com.coolron;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * 作者：恒宇少年
 * 链接：https://www.jianshu.com/p/9a08417e4e84
 * 來源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
//配置druid必须加的注解，如果不加，访问页面打不开，
// filter和servlet、listener之类的需要单独进行注册才能使用，spring boot里面提供了该注解起到注册作用
@ServletComponentScan
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.coolron.*.dao")
// 添加注解后SpringBoot就已经认定了我们要使用定时任务来完成一些业务逻辑了，内部会对应原始配置定时任务添加对应的配置文件。
// @EnableScheduling
@PropertySource(value = { "classpath:application.properties" })
public class Application {

	public static void main(String[] args) {
		/**
		 * 隐藏banner启动方式
		 */
		/*SpringApplication springApplication = new SpringApplication(Application.class);
		//设置banner的模式为隐藏
		springApplication.setBannerMode(Banner.Mode.OFF);
		//启动springboot应用程序
		springApplication.run(args);*/

		// 原始启动方式
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);

	}

}
