package com.coolron.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义配置类实现JavaBean注解形式配置
 *
 * WebMvcConfigurerAdapter:
 * 这个类是SpringBoot内部提供专门处理用户自行添加的配置，里面不仅仅包含了修改视图的过滤还有其他很多的方法
 * 包括 拦截器，过滤器，Cors配置等。
 *
 * WebMvcConfigurerAdapter配置类其实是Spring内部的一种配置方式，
 * 采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制
 *
 * 注解@Configuration，标明了该类是一个配置类并且会将该类作为一个SpringBean添加到IOC容器内
 * 打开 @Configuration 会看到
 * @Target({ElementType.TYPE})
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented
 * @Component  解释了为什么我们配置了@Configuration会被自动添加到IOC容器内。
 *
 * WebMvcConfigurerAdapter 该类为abstract 抽象的 里面没有任何的方法实现，只是空实现了接口WebMvcConfigurer内的全部方法，
 * 并没有给出任何的业务逻辑处理，这一点设计恰到好处的让我们不必去实现那些我们不用的方法，都交由WebMvcConfigurerAdapter抽象类空实现，
 * 如果我们需要针对具体的某一个方法做出逻辑处理，仅仅需要在WebMvcConfigurerAdapter子类中@Override对应方法就可以了。
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 解决Redis 客户端 key value 乱码 (类似 \xac\xed\x00\x05t\x00)
     */
//    @Autowired
//    private RedisTemplate redisTemplate;
//    @Bean
//    public RedisTemplate redisTemplateInit() {
//        //设置序列化Key的实例化对象
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //设置序列化Value的实例化对象
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return redisTemplate;
//    }

 /*   @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }*/

    /*
     * 拦截器配置
     * 在spring-mvc.xml配置文件内添加<mvc:interceptor>标签配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
       // registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**").excludePathPatterns("/login");
       // registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");

        //registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");


        // 父类的配置
        super.addInterceptors(registry);
    }

    /**
     * 配置 MessageConverter
     * 这个配置一般针对于Api接口服务程序，配置在请求返回时内容采用什么转换器进行转换，
     * 我们最常用到的就是fastJson的转换
     *
     * 内容转换都是针对面向接口进行编写的实现类，都必须 implements HttpMessageConverter接口完成方法的实现
     * 即 FastJsonHttpMessageConverter 中有实现 HttpMessageConverter接口
     * @param converters  消息转化器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        // 父类的配置
        super.configureMessageConverters(converters);
        // 创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 时间格式化 不然返回时间戳
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(

                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty
        );
        /**
         * fastJson配置实体调用setSerializerFeatures方法可以配置多个过滤方式
         * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
         * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * WriteMapNullValue：是否输出值为null的字段,默认为false。
         */
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }

    /**
     * 跨域CORS配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/cors/**")  // 跨域的路径
                .allowedHeaders("*")
                .allowedMethods("POST","GET")
                .allowedOrigins("*")   // IP  "http://192.168.1.97"
                .allowCredentials(false).maxAge(3600);

       /*   addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
        *   allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
        *   allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
        *   allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-YAUTH-TOKEN"
        */
    }

    /**
     * 视图控制器配置
     * 这一个配置在之前是经常被使用到的，最经常用到的就是"/"、"/index"路径请求时不通过@RequestMapping配置，
     * 而是直接通过配置文件映射指定请求路径到指定View页面，当然也是在请求目标页面时不需要做什么数据处理才可以这样使用
     * @param registry
     */
    //@Override
    //public void addViewControllers(ViewControllerRegistry registry) {
    //    super.addViewControllers(registry);
    //    registry.addViewController("/").setViewName("/index");
    //}

    /**
     * 配置ViewResolver
     * 只要我们配置html、Jsp页面视图时就会用到InternalResourceViewResolver配置类，
     * 然后设置preffix、suffix参数进行配置视图文件路径前缀与后缀
     * 配置请求视图映射
     * @return
     */
    //@Bean  // 该注解会将方法返回值加入到SpringIoc容器内。
    //public InternalResourceViewResolver resourceViewResolver()
    //{
    //    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    //    //请求视图文件的前缀地址
    //    internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
    //    //请求视图文件的后缀
    //    internalResourceViewResolver.setSuffix(".jsp");
    //    return internalResourceViewResolver;
    //}

    /**
     * 视图配置
     * @param registry
     */
    //@Override
    //public void configureViewResolvers(ViewResolverRegistry registry) {
    //    super.configureViewResolvers(registry);
    //    // configureViewResolvers方法内配置视图映射为resourceViewResolver方法返回的InternalResourceViewResolver实例，这样完成了视图的配置
    //    registry.viewResolver(resourceViewResolver());
    //    // 注释掉的一部分代码，这块代码很神奇  ViewResolverRegistry源码中 有 "/WEB-INF/jsp/",".jsp" 的配置
    //    /*registry.jsp("/WEB-INF/jsp/",".jsp");*/
    //}

}
