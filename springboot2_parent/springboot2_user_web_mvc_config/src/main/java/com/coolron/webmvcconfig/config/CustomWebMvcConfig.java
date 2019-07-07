package com.coolron.webmvcconfig.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.coolron.webmvcconfig.interceptor.MyInterceptor1;
import com.coolron.webmvcconfig.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: xf
 * @Date: 2019/1/8 18:25
 * @Description: springboot 2.0  自定义webmvc的配置
 * 方式：
 * 1、继承 WebMvcConfigurationSupport 会导致 webmvc 自动配置失效
 * 2、实现 WebMvcConfigurer 接口  推荐
 */
@Configuration
//public class CustomWebMvcConfig extends WebMvcConfigurationSupport{
public class CustomWebMvcConfig implements WebMvcConfigurer {

    /**
     * 拦截器配置
     * addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用户排除拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         // 多个拦截器会组成一个拦截器链
         registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**").excludePathPatterns("/login");
         registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    }

    /**
     * 消息转换器 -- alibaba 开源的 fastjson
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        /**
         * springboot 2.0 配置FastJson失效，添加如下配置
         *
         * 自定义的 FastJsonHttpMessageConverter 在 MappingJackson2HttpMessageConverter 后面，导致失效
         * 解决：加载FastJsonHttpMessageConverter 前删除 MappingJackson2HttpMessageConverter
         *
         * 参考链接：https://segmentfault.com/a/1190000015975405
         */
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }

        // 1、需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                // list null -> []
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                // String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                // Number null -> 0
                SerializerFeature.WriteNullNumberAsZero,
                // Boolean null -> false
                SerializerFeature.WriteNullBooleanAsFalse,
                //禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );

        // 3、处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // 4、在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 5、将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);

        /**
         * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
         * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * WriteMapNullValue：是否输出值为null的字段,默认为false
         */
    }
}
