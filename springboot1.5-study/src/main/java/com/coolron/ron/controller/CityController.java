package com.coolron.ron.controller;

import com.coolron.common.utils.HaoCangResult;
import com.coolron.ron.domain.City;
import com.coolron.ron.domain.User;
import com.coolron.ron.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
/**
 * Lombok为我们内置了各种日志组件的支持，我们在SpringBoot项目开发中几乎都是使用logback作为日志组件，而logback是基于slf4j完成的。
 * 所以我们在实体类上直接添加@Slf4j就可以自动创建一个日志对象作为类内全局字段 log
 * private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CityRestController.class);
 */
@Slf4j
@RequestMapping(value = "city")
public class CityController {
    /**
     * 在控制器中注入了一个MessageSource的接口对象，这个对象是用于格式化错误消息的
     * 根据传入的错误字段对象（FieldError）结合hibernate-validator验证的内置错误消息文件进行输出错误消息，
     * hibernate-validator的错误消息支持国际化，所以我们获取错误消息的时候需要传入Locale对象获取本地的国际化类型。
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * logback   SpringBoot内部集成了LogBack
     * LogBack读取配置文件的步骤
     （1）尝试classpath下查找文件logback-test.xml
     （2）如果文件不存在，尝试查找logback.xml
     （3）如果两个文件都不存在，LogBack用BasicConfiguration自动对自己进行最小化配置，这样既实现了上面我们不需要添加任何配置就可以输出到控制台日志信息。
      (4) 屏蔽日志 改变日志的输出级别   到properties文件中设置
     */
    // private final static Logger log = LoggerFactory.getLogger(CityRestController.class);  // 使用lombok @Slf4j 创建

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "cityList", method = RequestMethod.GET)
    public HaoCangResult getCityList(){
       List<City> cityList =  cityService.getCityList();
        return HaoCangResult.ok(cityList);
    }

    @RequestMapping(value = "findCityByName", method = RequestMethod.GET)
    public HaoCangResult findCityByName(@RequestParam(value = "cityName", required = true) String cityName) {
        //Long sessionId = System.currentTimeMillis();
        //log.info(sessionId + ":日志测试");
        //log.debug(sessionId + ":日志debug");
        //log.error(sessionId + ":日志error");
        //log.error(sessionId + ":日志lombok");

        City city = cityService.findCityByName(cityName);

        return HaoCangResult.ok(city);
    }

    /**
     * 测试数据校验  validator
     * @param user
     * @return
     */
    @RequestMapping(value = "/validator")
    public String validatorCity(@Valid User user, BindingResult result){

        if(result.hasErrors()){
            StringBuffer msg = new StringBuffer();
            //获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            //获取本地locale,zh_CN
            Locale currentLocale = LocaleContextHolder.getLocale();
            //遍历错误字段获取错误消息
            for (FieldError fieldError : fieldErrors) {
                //获取错误信息
                String errorMessage = messageSource.getMessage(fieldError,currentLocale);
                //添加到错误消息集合内
                msg.append(fieldError.getField()+"："+errorMessage+" , ");
            }
            return msg.toString();
        }
        return "验证通过，" + "名称：" + user.getName()+ "年龄：" + user.getAge() + "用户ID："+user.getId();
    }

    @RequestMapping(value = "findOneCity/{id}", method = RequestMethod.GET)
    public HaoCangResult findOneCity(@PathVariable("id") Integer id) {
        City city = cityService.findCityById(id);
        return HaoCangResult.ok(city);
    }

    @RequestMapping(value = "addCity", method = RequestMethod.POST)
    public HaoCangResult addCity(@RequestBody City city) {
        int result = cityService.saveCity(city);
        return HaoCangResult.ok(result);
    }

    @RequestMapping(value = "updateCity", method = RequestMethod.PUT)
    public HaoCangResult updateCity(@RequestBody City city) {
        int result = cityService.updateCity(city);
        return HaoCangResult.ok(result);
    }

    @RequestMapping(value = "deleteCity/{id}", method = RequestMethod.DELETE)
    public HaoCangResult deleteCity(@PathVariable("id") Integer id) {
        int result = cityService.deleteCity(id);
        return HaoCangResult.ok(result);
    }
}
