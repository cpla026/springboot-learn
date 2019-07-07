package com.coolron;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * lombok:
 * https://www.jianshu.com/p/9bd6ce692ab1
 *
 */
// 导入外部的spring配置文件  推荐使用 配置类（java的方式）
// @ImportResource(locations = "classpath:beans.xml")
// 加载指定的配置文件
// @PropertySource("classpath:custom.properties")
// filter和servlet、listener之类的需要单独进行注册才能使用，spring boot里面提供了该注解起到注册作用
@ServletComponentScan
// mapper 接口类扫描包配置
@MapperScan("com.coolron.*.dao")
@SpringBootApplication
// 开启定时支持
@EnableScheduling
@EnableAsync
public class SpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
